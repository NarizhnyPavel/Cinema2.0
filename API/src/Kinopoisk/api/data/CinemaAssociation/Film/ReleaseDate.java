package Kinopoisk.api.data.CinemaAssociation.Film;

import java.io.Serializable;

public class ReleaseDate implements Serializable {
    private int id;
    private String world;
    private String rus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReleaseDate() {
    }

    public ReleaseDate(String world, String rus) {
        this.world = world;
        this.rus = rus;
    }

    public ReleaseDate(int id, String world, String rus) {
        this.id = id;
        this.world = world;
        this.rus = rus;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    public String getDate() {
        return "в мире: " + world + " в России: " + rus;
    }

    public String getWorld() {
        return world;
    }

    public String getRus() {
        return rus;
    }
}
