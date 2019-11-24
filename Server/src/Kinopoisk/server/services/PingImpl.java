package Kinopoisk.server.services;

import Kinopoisk.api.services.Ping;
import Kinopoisk.server.UserPool;

public class PingImpl extends HorseHessianServlet implements Ping {
    @Override
    public boolean ping(String seanceId) {
        return UserPool.getUser(seanceId) != null;
    }
}
