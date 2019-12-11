package Kinopoisk.api.data.User;

import Kinopoisk.api.data.CinemaAssociation.Film.Genre;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Класс, описывающий пользователя системы
 * дополняет объект класса {@link @User}
 * полями для функционирования системы
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Viewer extends User implements Serializable {
    /** */
    private ArrayList<Genre> lovelyGenres; // system add genres, that user like // юзер абстрактный класс, остальные наследуют

    public Viewer(String userName, String password) {
        super(userName, password);
        lovelyGenres = new ArrayList<>();
    }
}
