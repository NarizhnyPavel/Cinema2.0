package Kinopoisk.server.services;

import Kinopoisk.api.data.Person.Person;
import Kinopoisk.api.data.User.User;
import Kinopoisk.api.services.AuthenticationService;
import Kinopoisk.server.UserPool;
import Kinopoisk.server.database.DatabaseManager;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AuthenticationServiceImplementation extends HorseHessianServlet implements AuthenticationService
{
    @Override
    public String login (final String login, final String password )
    {
        try
        {
            final List<User> list = DatabaseManager.getInstance ().get (
                    "select * from \"user\" where " +
                            "\"userName\" = '" + login + "' and " +
                            "password = '" + password + "'", User.class );
            if ( list.size () == 1 )
            {
                final User user = ( User ) list.get ( 0 );
                final String s = UUID.randomUUID ().toString ();
                UserPool.add ( s, user );
                return s;
            }
        }
        catch ( SQLException e )
        {
            e.printStackTrace ();
        }

        return null;
    }



    @Override
    public void logout(String seanceId) {
        UserPool.remove(seanceId);
    }
}
