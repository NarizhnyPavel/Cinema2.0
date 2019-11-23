package Kinopoisk.server.services;

import Kinopoisk.api.data.CinemaAssociation.CinemaAssociation;
import Kinopoisk.api.data.CinemaAssociation.Film.AgeLimit;
import Kinopoisk.api.data.CinemaAssociation.Film.Film;
import Kinopoisk.api.data.CinemaAssociation.Film.Genre;
import Kinopoisk.api.data.CinemaAssociation.Film.ReleaseDate;
import Kinopoisk.api.data.CinemaAssociation.TypeAssociation;
import Kinopoisk.api.data.CinemaStudio.CinemaStudio;
import Kinopoisk.api.data.Country.Country;
import Kinopoisk.api.data.FunClub.FunClub;
import Kinopoisk.api.data.Person.Person;
import Kinopoisk.api.data.Person.Profession;
import Kinopoisk.api.data.User.User;
import Kinopoisk.api.services.DataService;
import Kinopoisk.server.database.DatabaseManager;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

public class DataServiceImplementation extends HorseHessianServlet implements DataService {

    //////////CinemaAssociations/////////
    @Override
    public List<CinemaAssociation> getAssociations() {
        final List list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"CinemaAssociations\" order by id",
                    CinemaAssociation.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public CinemaAssociation getAssociation(String name) {
        final List list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"CinemaAssociations\" where name='" + name + "'",
                    CinemaAssociation.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return (CinemaAssociation) list.get(0);
    }


    public List<CinemaAssociation> getAssociationsOf(User moderator) {
        final List list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"CinemaAssociations\" where moderator=" + moderator.getId() + " order by id;",
                    CinemaAssociation.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public void createAssociation(CinemaAssociation association)  {
        try {
            DatabaseManager.getInstance().executeUpdate("INSERT INTO \"CinemaAssociations\" (name, type, moderator, \"funClub\") " +
                    "VALUES('" + association.getName() + "', " +
                    association.getType().getType() + ", " +
                    association.getMod().getId() + ", " +
                    (association.getClub() != null ? association.getClub().getId() : 0)+ ")");
            association.setId( ((CinemaAssociation)DatabaseManager.getInstance().get("select * from \"CinemaAssociations\" where name ='" + association.getName() + "'", CinemaAssociation.class).get(0)).getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAssociation(CinemaAssociation association) {
        try {
            DatabaseManager.getInstance().execute(
                    "Delete from \"CinemaAssociations\" where id=" + association.getId() + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAssociation(CinemaAssociation association) {
        try {
            DatabaseManager.getInstance().execute(
                    "update \"CinemaAssociations\" set " +
                            " name ='" + association.getName() + "'," +
                            " type =" + association.getType().getType() + "," +
                            " moderator = " + association.getMod().getId() + "," +
                            " \"funClub\" = " + (association.getClub() != null ? association.getClub().getId() : 0) +
                            " where id =" + association.getId() +" ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ////////////MODERATORS///////////

    public List<User> getModerators() {
        final List<User> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"user\" where role=" + 2 + " order by id", User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public User getModerator(String name) {
        final List<User> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"user\" where \"userName\"='" + name + "'", User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return ( User) list.get ( 0 );
    }

    @Override
    public void createModerator(User moderator) throws SQLException {
        DatabaseManager.getInstance().executeUpdate("INSERT INTO \"user\" (\"userName\", password, role) " +
                "VALUES('" + moderator.getUserName() + "', '" + moderator.getPassword() + "', 2)");
        moderator.setId(getModerator(moderator.getUserName()).getId());
    }

    public void deleteModerator(User moderator) {
        try {
            DatabaseManager.getInstance().execute(
                    "Delete from \"user\" where id=" + moderator.getId() + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModerator(User moderator) {
        try {
            DatabaseManager.getInstance().execute(
                    "update \"user\" set " +
                            " role =" + moderator.getRole().getId() + "," +
                            " \"userName\" ='"+ moderator.getUserName() +"'," +
                            " password = '" + moderator.getPassword() + "'," +
                            " \"lastSession\" = " + moderator.getLastSessionLong() + "" +
                            " WHERE id = " + moderator.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        final List<User> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"user\" where role=" + 3 + " order by id", User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public User getUser(String name) {
        final List<User> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"user\" where \"userName\"='" + name + "' order by id", User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return ( User) list.get ( 0 );
    }

    @Override
    public void createUser(User user)  {
        try {
            DatabaseManager.getInstance().executeUpdate("INSERT INTO \"user\" (\"userName\", password, role) " +
                    "VALUES('" + user.getId() + "', '" + user.getPassword() + "', 3)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        try {
            DatabaseManager.getInstance().execute(
                    "Delete from \"user\" where id=" + user.getId() + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            DatabaseManager.getInstance().execute(
                    "update \"user\" set " +
                            " role =" + user.getRole().getId() + ", " +
                            " \"userName\" = '" + user.getUserName() + "'," +
                            " password = '" + user.getPassword() + "'" + ", " +
                            " \"lastSession\" = " + user.getLastSessionLong() + " " +
                            " where id = " + user.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getSuperUser() {
        final List<User> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"user\" where role = 1 order by id", User.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return ( User) list.get ( 0 );
    }

    @Override
    public void updateSuperUser(User user) {
        try {
            DatabaseManager.getInstance().execute(
                    "update \"user\" set " +
                            " role = 1, " +
                            " \"userName\" = '" + user.getUserName() + "'," +
                            " password = '" + user.getPassword()+ "' " +
                            " WHERE  id =" + user.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ////////////TypesAss///////////

    @Override
    public TypeAssociation getTypeAssociation(int index) {
        final List list;
        try
        {
            list = DatabaseManager.getInstance ().get (
                    "select * from TypeAssociation where id='" + index + "'",
                    TypeAssociation.class );
        }
        catch ( SQLException e )
        {
            e.printStackTrace ();
            return null;
        }

        if ( list.isEmpty () )
        {
            return null;
        }
        return ( TypeAssociation ) list.get ( 0 );
    }

    ///////////Films////////////
    @Override
    public List<Film> getFilms() {
        final List<Film> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"films\" order by id", Film.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public Film getFilm(String name) {
        final List<Film> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"films\" where name='" + name + "' order by id", Film.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return ( Film) list.get ( 0 );
    }

    @Override
    public List<Film> getFilmsOf(CinemaAssociation association) {
        final List<Film> list;
        try {
            list = DatabaseManager.getInstance().get("select * from \"films\" where \"cinemaAss\"=" + association.getId() + " order by id", Film.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public void createFilm(Film film) {
        try {
            DatabaseManager.getInstance().executeUpdate("INSERT INTO \"films\" (\"cinemaAss\", name, \"releaseDate\", director, writer, country, \"cinemaStudio\", \"ageLimit\", genre) " +
                    "VALUES(" +  film.getCinemaAss().getId() + ", '" + film.getName() + "'," +
                    film.getReleaseDate().getId() + ", " + film.getDir().getId() + "," + film.getWriter().getId() + ", " +
                    film.getCountry().getId() + "," + film.getStudio().getId() + ", " + film.getAgeLim().getId() + ", " + film.getGenre().getId() + ")");
            film.setId(getFilm(film.getName()).getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFilm(Film film) {
        try {
            DatabaseManager.getInstance().execute(
                    "Delete from \"films\" where id=" + film.getId() + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFilm(Film film) {
        try {
            DatabaseManager.getInstance().execute(
                    "update \"films\" set " +
                            " \"cinemaAss\" = " + film.getCinemaAss().getId() + ", " +
                            " name = '" + film.getName() +  "'," +
                            " \"releaseDate\" = " + film.getReleaseDate().getId() + "," +
                            " director = " + film.getDir().getId() + "," +
                            " genre = " + film.getGenre().getId() + "," +
                            " \"ageLimit\" = " + film.getAgeLim().getId() + "," +
                            " writer = " + film.getWriter().getId() + "," +
                            " poster = '" + film.getPoster() + "', " +
                            " country = " + film.getCountry().getId() + "," +
                            " \"cinemaStudio\" = "+ film.getStudio().getId() + "" +
                            "WHERE id = " + film.getId() + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ////////////FunClubs////////////

    @Override
    public List<FunClub> getFunClubs() {
        final List<FunClub> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"funclubs\" order by id", FunClub.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public FunClub getFunClub(String name) {
        final List<FunClub> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"funclubs\" where name='" + name + "' order by id", FunClub.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return ( FunClub) list.get ( 0 );
    }

    @Override
    public FunClub getFunClub(int id) {
        final List<FunClub> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"funclubs\" where id=" + id + " order by id", FunClub.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return ( FunClub) list.get ( 0 );
    }

    @Override
    public void createFunClub(FunClub funClub) {
        try {
            DatabaseManager.getInstance().executeUpdate("INSERT INTO \"funclubs\" (name, chat) " +
                    "VALUES('" + funClub.getName() + "', '" + funClub.getChat() + "')");
            funClub.setId(getFunClub(funClub.getName()).getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFunClub(FunClub funClub) {
        try {
            DatabaseManager.getInstance().execute(
                    "Delete from \"funclubs\" where id=" + funClub.getId() + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFunClub(FunClub funClub) {
        try {
            DatabaseManager.getInstance().execute(
                    "update \"funclubs\" set " +
                            " name = '" + funClub.getName() +  "'," +
                            " chat = '" + funClub.getChat() + "' " +
                            "WHERE id = " + funClub.getId() + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ////////////Persons////////////

    public List<Person> getPersons() {
        final List<Person> list;
        try {
            list = DatabaseManager.getInstance().get("select * from \"persons\" order by id", Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<Person> getPersonsWho(Profession profession) {
        final List<Person> list;
        try {
            list = DatabaseManager.getInstance().get("select * from \"persons\" where prof = " + profession.getId() + " order by id;", Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public Person getPerson(String name) {
        final List<Person> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"persons\" where name='" + name + "' order by id", Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return (Person) list.get ( 0 );
    }

    public int createPerson(Person person) {
        try {
            DatabaseManager.getInstance().executeUpdate("INSERT INTO \"persons\" (name, prof, \"birthDate\", country, funclub) " +
                    "VALUES('" + person.getName() + "', " + person.getProf().getId() + ", '" + person.getBirthDate() + "'," +
                    person.getBirthCountry().getId() + "," + (person.getFunclub() != null ? person.getFunclub().getId() : 0) + ")");
            person.setId(getPerson(person.getName()).getId());
            return person.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void deletePerson(Person person) {
        try {
            DatabaseManager.getInstance().execute(
                    "Delete from \"persons\" where id=" + person.getId() + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePerson(Person person) {
        try {
            DatabaseManager.getInstance().execute(
                    "update \"persons\" set " +
                            " name = '" + person.getName() +  "'," +
                            " prof = " + person.getProf().getId() + "," +
                            " \"birthDate\" = '" + person.getBirthDate()+ "'," +
                            " country = " + person.getBirthCountry().getId() + "," +
                            " funclub = "+ person.getFunclub().getId() + "" +
                            "WHERE id = " + person.getId() + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ////////////Countries////////////

    public List<Country> getCountries(){
        final List<Country> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"countries\" order by id", Country.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;

    }

    public Country getCountry(String name) {
        final List<Country> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from countries where name='" + name + "' order by id",
                    Country.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if (list.size() != 0)
            return ( Country ) list.get ( 0 );
        else
            return null;
    }

    @Override
    public Country getCountry(int id) {
        final List<Country> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from countries where id=" + id + " order by id;",
                    Country.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ( Country ) list.get ( 0 );
    }

    public int createCountry(String name) {
        int id = 0;
        try {
            DatabaseManager.getInstance().executeUpdate("INSERT INTO \"countries\" (name) VALUES('" + name + "');");

            id = getCountry(name).getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    ////////////Studios////////////

    @Override
    public List<CinemaStudio> getCinemaStudios() {
        final List<CinemaStudio> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"studios\" order by id", CinemaStudio.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public CinemaStudio getCinemaStudio(String name) {
        final List list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from studios where name='" + name + "';",
                    CinemaStudio.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ( CinemaStudio ) list.get ( 0 );
    }

    @Override
    public CinemaStudio getCinemaStudio(int id) {
        final List list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from studios where id=" + id + ";",
                    CinemaStudio.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ( CinemaStudio ) list.get ( 0 );
    }

    @Override
    public int createCinemaStudio(CinemaStudio studio) {
        int id = 0;
        try {
            DatabaseManager.getInstance().executeUpdate("INSERT INTO \"studios\" (name, \"createDate\", country) " +
                    "VALUES('" + studio.getName() + "', '" + studio.getCreateDate() +"', " + studio.getNativeCountry().getId() + ");");

            id = getCinemaStudio(studio.getName()).getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void deleteCinemaStudio(CinemaStudio studio) {
        try {
            DatabaseManager.getInstance().execute(
                    "Delete from \"studios\" where id=" + studio.getId() + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCinemaStudio(CinemaStudio studio) {
        try {
            DatabaseManager.getInstance().execute(
                    "update \"studios\" set " +
                            " name = '" + studio.getName() +  "'," +
                            " \"createDate\" = '" + studio.getCreateDate() + "'," +
                            " country = " + studio.getNativeCountry().getId() +  "," +
                            "WHERE id = " + studio.getId() + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ReleaseDate> getReleaseDates() {
        final List<ReleaseDate> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"releasedates\" order by id", ReleaseDate.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public ReleaseDate getReleaseDate(int id) {
        final List<ReleaseDate> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"releasedates\" where id = " + id + " order by id;", ReleaseDate.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return (ReleaseDate) list.get(0);
    }

    @Override
    public int createReleaseDate(ReleaseDate releaseDate) {
        try {
            DatabaseManager.getInstance().executeUpdate("INSERT INTO \"releasedates\" (world, rus) " +
                    "VALUES('" + releaseDate.getWorld() + "', '" + releaseDate.getRus() + "')");
            return ((ReleaseDate) (DatabaseManager.getInstance().get(
                    "select * from \"releasedates\" where world = '" + releaseDate.getWorld() + "' AND rus = '" + releaseDate.getRus() + "' ;", ReleaseDate.class).get(0))).getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateReleaseDate(ReleaseDate releaseDate) {
        try {
            DatabaseManager.getInstance().execute("update \"releasedates\" set " +
                            " world = '" + releaseDate.getWorld()  +  "'," +
                            " rus = '" + releaseDate.getRus() +  "'" +
                            "WHERE id = " + releaseDate.getId() + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AgeLimit> getAgeLimits(){
        final List<AgeLimit> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"AgeLimits\" order by id", AgeLimit.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<Genre> getGenres() {
        final List<Genre> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"genres\" order by id", Genre.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public List<Profession> getProfessions(){
        final List<Profession> list;
        try {
            list = DatabaseManager.getInstance().get(
                    "select * from \"Professions\" order by id", Profession.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
}
