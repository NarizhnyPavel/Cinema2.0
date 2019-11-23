package Kinopoisk.api.data.User;

import Kinopoisk.api.data.CinemaAssociation.Film.Genre;
import Kinopoisk.api.data.User.User;

import java.io.Serializable;

public class Viewer extends User implements Serializable {
    private String userName;
    private Genre lovelyGenres[]; // system add genres, that user like // юзер абстрактный класс, остальные наследуют

    public Viewer(String userName, String password) {
        super(userName, password);
        this.userName = userName;
    }
}
