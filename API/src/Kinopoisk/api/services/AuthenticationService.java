package Kinopoisk.api.services;

public interface AuthenticationService
{
    String login(String login, String password);

    void logout(String seanceId);
}
