package Kinopoisk.server;

import Kinopoisk.api.data.User.User;

import java.util.*;

public class UserPool {
    private static Map<String, User> seancePool = new HashMap<>();
    private static Map<String, Long> touchPool = new HashMap<>();
    private static Map<String, LogoutTimer> timersPool = new HashMap<>();

    public static User getUser(String seanceId) {
        return seancePool.get(seanceId);
    }

    public static void add(String seanceId, User user) {
        seancePool.put(seanceId, user);
        touch(seanceId);
    }

    public static void touch(String seanceId) {
        touchPool.put(seanceId, System.currentTimeMillis());
        if (timersPool.get(seanceId) == null)
            timersPool.put(seanceId, new LogoutTimer(seanceId));
        else
            timersPool.get(seanceId).restartTimer();
    }

    public static void remove(String seanceId) {
        seancePool.remove(seanceId);
        touchPool.remove(seanceId);
        timersPool.remove(seanceId);
    }

    public static void trashMap(){
        /*touchPool.clear();
        if (timersPool.size() != 0)
            for (int i = 0; i < timersPool.size(); i++)
                timersPool.get(seancePool.).restartTimer();*/
    }

    private static class TimerTask extends java.util.TimerTask{
        private String seanceId = "";
        @Override
        public void run() {
            remove(seanceId);
            timer.cancel();
        }

        public void setSeanceId(String seanceId){
            this.seanceId = seanceId;
        }
    }
    private static Timer timer;
    private static class LogoutTimer  {
        private TimerTask timerTask = new TimerTask();

        LogoutTimer(String sessionId) {
            timerTask.setSeanceId(sessionId);
            timer = new Timer(true);
            // будем запускать каждых 10 секунд (10 * 1000 миллисекунд)
            timer.scheduleAtFixedRate(timerTask, 1 * 15 * 1000, 1 * 35 * 1000);
            System.out.println("LogoutTimer начал выполнение");

        }

        public void restartTimer(){
            timer.cancel();
            timer.scheduleAtFixedRate(timerTask, 1 * 15 * 1000, 1 * 35 * 1000);
        }

        public void stopTimer(){
            timer.cancel();
        }
    }

}
