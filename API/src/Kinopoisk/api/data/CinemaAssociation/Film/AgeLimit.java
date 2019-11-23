package Kinopoisk.api.data.CinemaAssociation.Film;

import java.io.Serializable;

public class AgeLimit implements Serializable {
    private int id;
    private int limit;
    private String discript;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AgeLimit(int id, int limit, String discript) {
        this.id = id;
        this.limit = limit;
        this.discript = discript;
    }

    public AgeLimit() {
    }

    public int getLimit() {
        return limit;
    }

    public String getLimitString(){
        return discript;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getDiscript() {
        return discript;
    }

    public void setDiscript(String discript) {
        this.discript = discript;
    }
}