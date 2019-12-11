package Kinopoisk.api.data.Person;

import Kinopoisk.api.data.Country.Country;

import java.io.Serializable;

/**
 * Класс, описывающий персону
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Person implements Serializable {
    /** числовой идентификатор*/
    private int id;
    /** имя персоны*/
    private String name;
    /** профессия {@link Profession}*/
    private Profession prof;
    /** дата рождения*/
    private String birthDate;
    /** страна рождения {@link Country}*/
    private Country birthCountry;

    public Person() {
    }

    public Person(String name, Profession prof, String birthDate, Country birthCountry) {
        this.name = name;
        this.prof = prof;
        this.birthDate = birthDate;
        this.birthCountry = birthCountry;
    }

    public Person(int id, String name, Profession prof, String birthDate, Country birthCountry) {
        this.id = id;
        this.name = name;
        this.prof = prof;
        this.birthDate = birthDate;
        this.birthCountry = birthCountry;
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

    public void setProf(Profession prof) {
        this.prof = prof;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthCountry(Country birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getName() {
        return name;
    }

    public Profession getProf() {
        return prof;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Country getBirthCountry() {
        return birthCountry;
    }

}
