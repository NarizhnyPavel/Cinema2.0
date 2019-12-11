package Kinopoisk.server.database;

import Kinopoisk.api.data.CinemaAssociation.CinemaAssociation;
import Kinopoisk.api.data.CinemaAssociation.Film.AgeLimit;
import Kinopoisk.api.data.CinemaAssociation.Film.Film;
import Kinopoisk.api.data.CinemaAssociation.Film.Genre;
import Kinopoisk.api.data.CinemaAssociation.Film.ReleaseDate;
import Kinopoisk.api.data.CinemaAssociation.TypeAssociation;
import Kinopoisk.api.data.CinemaStudio.CinemaStudio;
import Kinopoisk.api.data.Country.Country;
import Kinopoisk.api.data.Person.Person;
import Kinopoisk.api.data.Person.Profession;
import Kinopoisk.api.data.User.User;
import Kinopoisk.api.data.User.UserRole;
import Kinopoisk.api.services.DataService;
import Kinopoisk.server.services.DataServiceImplementation;
import org.postgresql.Driver;

import javax.xml.crypto.dsig.Reference;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий методы по инициализации соединения с СУБД Postegrace и обработке SQL запросов
 */
public class DatabaseManager
{
    private static DatabaseManager instance;
    private Connection connection;

    /**
     * инициализация соединения с локальной СУБД
     */
    private DatabaseManager ()
    {
        try
        {
            Class.forName ( Driver.class.getCanonicalName () );
            connection = DriverManager.getConnection (
                    "jdbc:postgresql://localhost/DataForKino",
                    "postgres", "gfdtk145" );
        }
        catch ( SQLException | ClassNotFoundException e )
        {
            e.printStackTrace ();
        }
    }

    public static synchronized DatabaseManager getInstance ()
    {
        if ( instance == null )
        {
            instance = new DatabaseManager ();
        }
        return instance;
    }

    public void execute ( final String sql ) throws SQLException
    {
        final Statement statement = connection.createStatement ();
        final boolean execute = statement.execute ( sql );
        statement.close ();
    }

    public int executeUpdate ( final String sql ) throws SQLException
    {
        final Statement statement = connection.createStatement ();
        final int count = statement.executeUpdate ( sql );
        statement.close ();
        return count;
    }

    public List get ( final String sql, Class clazz ) throws SQLException
    {
        final Statement statement = connection.createStatement ();
        final ResultSet resultSet = statement.executeQuery ( sql );
        List result = new ArrayList ();
        while ( resultSet.next () )
        {
            result.add ( buildObject ( resultSet, clazz ) );
        }
        statement.close();
        return result;
    }

    /**
     * метод возвращающий объект класса, исходя из ответа СУБД
     * @param resultSet - параметр, возвращаемый СУБД. содержит ответ на запрос
     * @param clazz - имя класса, объект которого необходимо "построить"
     * @return - объект заданного класса
     * @throws SQLException
     */
    private Object buildObject ( final ResultSet resultSet, final Class clazz ) throws SQLException
    {
        if ( clazz.equals ( User.class ) )
        {
            final User user = new User(resultSet.getLong( "id" ), resultSet.getString ( "userName" ), new UserRole().getUserRole(resultSet.getInt("role")), resultSet.getString ( "password" ), resultSet.getLong("lastSession") );
            if (user.getLastSessionLong() == 0) {
                user.updateSession();
                try {
                    DatabaseManager.getInstance().execute(
                            "update \"user\" set " +
                                    " \"lastSession\" = " + user.getLastSessionLong() + "" +
                                    " WHERE id = " + user.getId() + "");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return user;
        }

        if ( clazz.equals (CinemaAssociation.class) )
        {
            final CinemaAssociation association = new CinemaAssociation (  );
            association.setId(resultSet.getInt("id"));
            association.setName(resultSet.getString ( "name" ));
            association.setType(new TypeAssociation().getType(resultSet.getInt ( "type" )));
            association.setMod((User) DatabaseManager.getInstance().get("select * from \"user\" where id=" + resultSet.getInt("moderator") + ";", User.class).get(0));
            //association.setClub((FunClub) DatabaseManager.getInstance().get("select * from \"funclubs\" where id=" + resultSet.getInt("funClub") + ";", FunClub.class).get(0));

            return association;
        }

        if (clazz.equals(TypeAssociation.class))
        {
            final TypeAssociation typeAssociation = new TypeAssociation();
            typeAssociation.setType(resultSet.getString("name"));

            return typeAssociation;
        }

        if (clazz.equals(Film.class))
        {
            final Film film = new Film();
            film.setId(resultSet.getInt("id"));
            film.setCinemaAss((CinemaAssociation)get("SELECT * FROM \"CinemaAssociations\" WHERE id =" + resultSet.getInt("cinemaAss") + ";", CinemaAssociation.class).get(0));
            film.setCountry((Country) get("select * from countries where id=" + resultSet.getInt("country") + ";", Country.class).get(0));
            film.setName(resultSet.getString("name"));
            film.setPoster(resultSet.getString("poster"));
            film.setGenre((Genre) get("select * from \"genres\" where id=" + resultSet.getInt("genre") + ";", Genre.class).get(0));
            film.setAgeLim((AgeLimit) get("select * from \"AgeLimits\" where id=" + resultSet.getInt("ageLimit") + ";", AgeLimit.class).get(0));
            film.setReleaseDate((ReleaseDate) get("SELECT * FROM releasedates WHERE id = " + resultSet.getInt("releaseDate") + ";", ReleaseDate.class).get(0));
            film.setDir((Person) get("SELECT * FROM persons WHERE id = " + resultSet.getInt("director") + ";", Person.class).get(0));
            film.setWriter((Person) get("SELECT * FROM persons WHERE id = " + resultSet.getInt("writer") + ";", Person.class).get(0));
            film.setStudio((CinemaStudio) get("SELECT * FROM studios WHERE id = " + resultSet.getInt("cinemaStudio") + ";", CinemaStudio.class).get(0));
            return film;
        }

        if (clazz.equals(AgeLimit.class)){
            final AgeLimit ageLimit = new AgeLimit();
            ageLimit.setId(resultSet.getInt("id"));
            ageLimit.setLimit(resultSet.getInt("limit"));
            ageLimit.setDiscript(resultSet.getString("discript"));
            return ageLimit;
        }

        if(clazz.equals(ReleaseDate.class)){
            final ReleaseDate releaseDate = new ReleaseDate();
            releaseDate.setId(resultSet.getInt("id"));
            releaseDate.setWorld(resultSet.getString("world"));
            releaseDate.setRus(resultSet.getString("rus"));
            return releaseDate;
        }

        if (clazz.equals(Genre.class)){
            final Genre genre = new Genre();
            genre.setId(resultSet.getInt("id"));
            genre.setDiscript(resultSet.getString("name"));
            return genre;
        }

        if (clazz.equals(Profession.class)){
            final Profession profession = new Profession();
            profession.setId(resultSet.getInt("id"));
            profession.setName(resultSet.getString("name"));
            return profession;
        }

        if (clazz.equals(Person.class))
        {
            final Person person = new Person();
            person.setName(resultSet.getString("name"));
            person.setId(resultSet.getInt("id"));
            person.setProf((Profession) get("select * from \"Professions\" where id=" + resultSet.getInt("prof") + ";", Profession.class).get(0));
            person.setBirthDate(resultSet.getString("birthDate"));
            person.setBirthCountry((Country) get("select * from countries where id=" + resultSet.getInt("country") + ";", Country.class).get(0));
            //person.setFunclub(dataService.getFunClub(resultSet.getInt("finclub")));

            return person;
        }

        if (clazz.equals(Country.class)) {
            final Country country = new Country();
            country.setId(resultSet.getInt("id"));
            country.setName(resultSet.getString("name"));

            return country;
        }

        if (clazz.equals(CinemaStudio.class)) {
            final CinemaStudio studio = new CinemaStudio();
            studio.setId(resultSet.getInt("id"));
            studio.setName(resultSet.getString("name"));
            studio.setCreateDate(resultSet.getString("createDate"));
            studio.setNativeCountry((Country) get("select * from countries where id=" + resultSet.getInt("country") + ";", Country.class).get(0));

            return studio;
        }
        return null;
    }
}
