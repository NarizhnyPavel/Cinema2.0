package Kinopoisk.api.data.Person;

import java.io.Serializable;

/**
 * Класс, описывающий профессию для {@link Person}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Profession implements Serializable {
    /** числовой идентификатор*/
    private int id;
    /** название профессии*/
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
