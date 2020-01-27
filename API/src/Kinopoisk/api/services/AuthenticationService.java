package Kinopoisk.api.services;

import java.sql.SQLException;

/**
 * Интерфейс, который описывает методы авторизации пользователей в системе
 * @author Narizhny Pavel
 * @version 1.0
 */
public interface AuthenticationService
{
    String login(String login, char[] password) throws SQLException;

    void logout(String seanceId);
}
