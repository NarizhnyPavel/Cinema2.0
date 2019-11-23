package Kinopoisk.api.data.CinemaAssociation.Film;

import java.io.Serializable;

public class Genre implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiscript(String discript) {
        this.name = discript;
    }

    public Genre(String single) {
        this.name = single;
    }

    public String getDiscript() {
        return name;
    }

    public Genre() {
    }
}
