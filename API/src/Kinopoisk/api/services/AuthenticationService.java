package Kinopoisk.api.services;

/**
 * Интерфейс, который описывает методы авторизации пользователей в системе
 * @author Narizhny Pavel
 * @version 1.0
 */
public interface AuthenticationService
{
    String login(String login, String password);

    void logout(String seanceId);
}
