package Kinopoisk.api.services;

import Kinopoisk.api.data.User.User;

public interface AuthenticationService
{
    String login(String login, String password);

    void logout(String seanceId);
}
