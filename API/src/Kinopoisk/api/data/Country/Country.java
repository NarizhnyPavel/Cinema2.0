package Kinopoisk.api.data.Country;

import java.io.Serializable;
import java.util.Comparator;

public class Country implements Serializable {
    private int id;
    private String name;

    public Country() {
    }

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Comparators {

        public static Comparator<Country> NAME = new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.name.compareTo(o2.name);
            }
        };
    }
}
