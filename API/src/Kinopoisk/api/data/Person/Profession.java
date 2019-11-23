package Kinopoisk.api.data.Person;

import java.io.Serializable;

public class Profession implements Serializable {
    private int id;
    private String name;

    public Profession(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Profession() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
