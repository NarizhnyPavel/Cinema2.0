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
        timersPool.get(seanceId).stopTimer();
        timersPool.remove(seanceId);
    }

    public static void trashMap(){
        /*touchPool.clear();
        if (timersPool.size() != 0)
            for (int i = 0; i < timersPool.size(); i++)
                timersPool.get(seancePool.).restartTimer();*/
    }

    private static int i = 0;
    private static class LogoutTimer  {
        private static Timer timer;
        private TimerTask timerTask;
        private static int id;
        private static String sessionId;

        LogoutTimer(String newSessionId) {
            sessionId = newSessionId;
            setTimerTask();
            timer = new Timer();
            id = i++;
            // будем запускать каждых 10 секунд (10 * 1000 миллисекунд)
            timer.schedule(timerTask, 1 * 20 * 1000);
            System.out.println("LogoutTimer начал выполнение " + id + " для " + sessionId);
        }

        void setTimerTask() {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    remove(sessionId);
                    System.out.println("остановлен LogoutTimer для " + sessionId);
                }
            };
        }

        void restartTimer(){
            timer.cancel();
            timer = new Timer();
            setTimerTask();
            System.out.println("был перезапущен " + id);
            timer.schedule(timerTask, 1 * 20 * 1000);
        }

        void stopTimer(){
            timer.cancel();
        }
    }

}
