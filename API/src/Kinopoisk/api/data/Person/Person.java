package Kinopoisk.api.data.Person;

import Kinopoisk.api.data.Country.Country;
import Kinopoisk.api.data.FunClub.FunClub;

import java.io.Serializable;
import java.util.Comparator;

public class Person implements Serializable {
    private int id;
    private String name;
    private Profession prof;
    private String birthDate;
    private Country birthCountry;
    private FunClub funclub;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person(String name, Profession prof, String birthDate, Country birthCountry) {
        this.name = name;
        this.prof = prof;
        this.birthDate = birthDate;
        this.birthCountry = birthCountry;
    }

    public Person() {
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

    public Person(int id, String name, Profession prof, String birthDate, Country birthCountry) {
        this.id = id;
        this.name = name;
        this.prof = prof;
        this.birthDate = birthDate;
        this.birthCountry = birthCountry;
    }

    public Person(String name, Profession prof, String birthDate, Country birthCountry, FunClub funclub) {
        this.name = name;
        this.prof = prof;
        this.birthDate = birthDate;
        this.birthCountry = birthCountry;
        this.funclub = funclub;
    }

    public FunClub getFunclub() {
        return funclub;
    }

    public void setFunclub(FunClub funclub) {
        this.funclub = funclub;
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

    public String getInfo(){
        return name + " birthday: " + birthDate + " native Country: " + birthCountry.getName();
    }

    public static class Comparators {
        public static Comparator<Person> NAME = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.name.compareTo(o2.name);
            }
        };
    }
}
