package Kinopoisk.client.connection;

import Kinopoisk.api.services.AuthenticationService;
import Kinopoisk.api.services.DataService;
import Kinopoisk.api.services.Ping;
import com.caucho.hessian.client.*;
import com.caucho.hessian.io.HessianRemoteObject;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Класс, описывающий инициализацию соединения с сервером и инициализацию интерфейсов на стороне клиентов
 * @author Narizhny Pavel
 * @version 1.0
 */
public class ConnectionManager {
    private static ConnectionManager instance;
    private AuthenticationService authService;
    private DataService dataService;
    private static Ping ping;
    private static String url = "http://localhost:8085";

    private static String seanceId = null;
    private ConnectionManager() {

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
                            String code = e.getMessage().substring(0, 4);
                            if(code.equals("402:") && seanceId != null)
                                JOptionPane.showMessageDialog(null,"Произошёл автоматический выход из системы.");
                                System.exit(-1);
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

    public void setSeanceId(final String newSeanceId) {
        this.seanceId = newSeanceId;
    }

    public static void setUrl(String newUrl) {
        url = newUrl;
    }
}
