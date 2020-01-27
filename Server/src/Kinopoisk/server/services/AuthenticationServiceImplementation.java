package Kinopoisk.server.services;

import Kinopoisk.api.data.Person.Person;
import Kinopoisk.api.data.User.User;
import Kinopoisk.api.services.AuthenticationService;
import Kinopoisk.server.UserPool;
import Kinopoisk.server.database.DatabaseManager;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Класс, реалзующий методы сервиса {@link AuthenticationService}
 */
public class AuthenticationServiceImplementation extends HorseHessianServlet implements AuthenticationService
{
    /**
     * метод, авторизующий пользователя с переданными полями:
     * @param login - имя пользователя в системе
     * @param password - пароль пользователя
     * @return в случае успешной авторизации: идентификатор сеанса, в ином случае: код ошибки
     */
    @Override
    public String login (final String login, final char[] password ) throws SQLException {
        try
        {
            final List<User> list = DatabaseManager.getInstance ().get (
                    "select * from \"user\" where " +
                            "\"userName\" = '" + login + "' and " +
                            "password = '" + String.valueOf(password) + "'", User.class );
            if ( list.size () == 1 )
            {
                final User user = ( User ) list.get ( 0 );
                final String s = UUID.randomUUID ().toString (); // get seanceId
                UserPool.add ( s, user );
                return s;
            }
        }
        catch ( SQLException e )
        {
            e.printStackTrace ();
        }
        if (DatabaseManager.getInstance ().get (
                "select * from \"user\" where " +
                        "\"userName\" = '" + login + "'", User.class ).size() == 1)
            return "passError";
        if (DatabaseManager.getInstance ().get (
                "select * from \"user\" where " +
                        "\"userName\" = '" + login + "'", User.class ).size() == 0)
            return "nameError";
        return "mur";
    }

    /**
     * метод для выхода пользователя из системы по данному
     * @param seanceId - идентификатору сеанса
     */
    @Override
    public void logout(String seanceId) {
        UserPool.remove(seanceId);
    }
}
