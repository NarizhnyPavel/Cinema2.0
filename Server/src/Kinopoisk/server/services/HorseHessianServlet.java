package Kinopoisk.server.services;

import Kinopoisk.api.services.AuthenticationService;
import Kinopoisk.api.services.Ping;
import Kinopoisk.server.UserPool;
import com.caucho.hessian.server.HessianServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс, инициализирующий сервис Hessian Servlets для передачи пакетов между клиентом и сервером через веб интерфейс
 * @author Kondrat'eva N.U.
 * @version 1.0
 */
public class HorseHessianServlet extends HessianServlet
{
    private static final ThreadLocal<String> seanceID = new ThreadLocal<> ();

    @Override
    public void service ( final ServletRequest request, final ServletResponse response ) throws IOException, ServletException {
        seanceID.set ( ( ( HttpServletRequest ) request ).getHeader ( "seanceID" ) );
        if ( this instanceof AuthenticationService || this instanceof Ping) {
            super.service ( request, response );
        }
        else if ( ( seanceID.get () != null && UserPool.getUser ( seanceID.get () ) != null ) )
        {
            super.service ( request, response );
            UserPool.touch(seanceID.get());
        }
        else
        {
            ( ( HttpServletResponse ) response ).setStatus ( 402 );
        }
    }
}
