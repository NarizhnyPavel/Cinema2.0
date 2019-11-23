package Kinopoisk.client.connection;

import Kinopoisk.api.services.AuthenticationService;
import Kinopoisk.api.services.DataService;
import Kinopoisk.api.services.Ping;
import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianConnectionFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.client.HessianURLConnectionFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManager {
    private static ConnectionManager instance;
    private AuthenticationService authService;
    private DataService dataService;
    private Ping ping;

    private String seanceId = null;

    private ConnectionManager() {
        final String url = "http://localhost:8085";

        final HessianProxyFactory factory = new HessianProxyFactory() {
            @Override
            protected HessianConnectionFactory createHessianConnectionFactory() {
                final String className = System.getProperty(HessianConnectionFactory.class.getName());
                final HessianConnectionFactory factory;
                try {
                    if (className != null) {
                        final ClassLoader e = Thread.currentThread().getContextClassLoader();
                        final Class cl = Class.forName(className, false, e);
                        factory = (HessianConnectionFactory) cl.newInstance();
                        return factory;
                    }
                } catch (final Exception var5) {
                    throw new RuntimeException(var5);
                }

                return new HessianURLConnectionFactory() {
                    @Override
                    public HessianConnection open(final URL url) throws IOException {
                        final HessianConnection open = super.open(url);
                        if (seanceId != null) {
                            open.addHeader("seanceID", seanceId);
                        }
                        return open;
                    }
                };
            }
        };
        factory.setOverloadEnabled(true);
        try {
            authService = (AuthenticationService) factory.create(AuthenticationService.class, url + "/AuthenticationService");
            dataService = (DataService) factory.create(DataService.class, url + "/DataService");
            ping = (Ping) factory.create(Ping.class, url + "/Ping");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public AuthenticationService getAuthService() {
        return authService;
    }

    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(final DataService dataService) {
        this.dataService = dataService;
    }

    public void setSeanceId(final String seanceId) {
        this.seanceId = seanceId;
    }

}
