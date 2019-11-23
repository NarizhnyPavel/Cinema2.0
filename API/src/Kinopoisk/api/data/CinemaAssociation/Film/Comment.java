package Kinopoisk.api.data.CinemaAssociation.Film;

import Kinopoisk.api.data.User.User;

public class Comment {
    private Film film;
    private String text;
    private String date;
    private User author;

    public Comment(Film film, String text, String date, User author) {
        this.film = film;
        this.text = text;
        this.date = date;
        this.author = author;
    }
}
