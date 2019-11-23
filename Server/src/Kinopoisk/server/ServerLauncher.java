package Kinopoisk.server;

import Kinopoisk.server.database.DatabaseManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerLauncher implements ServletContextListener
{
    @Override
    public void contextInitialized ( final ServletContextEvent servletContextEvent )
    {
        DatabaseManager.getInstance ();
    }

    @Override
    public void contextDestroyed ( final ServletContextEvent servletContextEvent )
    {

    }
}
