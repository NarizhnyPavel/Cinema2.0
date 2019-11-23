package Kinopoisk.server.services;

import Kinopoisk.api.services.Ping;

public class PingImpl extends HorseHessianServlet implements Ping {
    @Override
    public boolean ping() {
        return true;
    }
}
