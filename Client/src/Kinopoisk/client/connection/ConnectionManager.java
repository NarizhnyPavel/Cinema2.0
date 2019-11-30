package Kinopoisk.client.connection;


import Kinopoisk.api.services.AuthenticationService;
import Kinopoisk.api.services.DataService;
import Kinopoisk.api.services.Ping;
import Kinopoisk.client.UI.Login;
import com.caucho.hessian.client.*;
import com.caucho.hessian.io.HessianRemoteObject;
import javafx.application.Application;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionManager {
    private static ConnectionManager instance;
    private AuthenticationService authService;
    private DataService dataService;
    private static Ping ping;

    private static String seanceId = null;

    /**
     *
     */
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
            @Override
            public Object create(final Class<?> api, final URL url, final ClassLoader loader) {
                final InvocationHandler handler = new HessianProxy(url, this, api) {
                    @Override
                    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                        try {
                            Object invoke = super.invoke(proxy, method, args);
                            return invoke;
                        } catch (final Exception e) {
                            String code =e.getMessage().substring(0, 4);
                            if(code.equals("402:") && seanceId != null)
                                JOptionPane.showMessageDialog(null,"Вас кинули");
                                System.exit(-1);
//                            System.out.println("Error: ");
                        }
                        return null;
                    }
                };
                return Proxy.newProxyInstance(loader, new Class[]{api, HessianRemoteObject.class}, handler);
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
        if (instance == null)
            instance = new ConnectionManager();
        /*if (!getPing()) {
            System.out.println("application closed cause time out");
            //System.exit(0);
        }*/
        return instance;
    }

    private static boolean getPing(){
        return ping.ping(seanceId);
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

    public void setSeanceId(final String newSeanceId) {
        this.seanceId = newSeanceId;
    }

}
