package Kinopoisk.api.data.CinemaStudio;

import Kinopoisk.api.data.Country.Country;

import java.io.Serializable;

public class CinemaStudio implements Serializable {
    private int id;
    private String name;
    private String createDate;
    private Country nativeCountry;

    public CinemaStudio() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Country getNativeCountry() {
        return nativeCountry;
    }

    public void setNativeCountry(Country nativeCountry) {
        this.nativeCountry = nativeCountry;
    }

    public CinemaStudio(String name, String createDate, Country nativeCountry) {
        this.name = name;
        this.createDate = createDate;
        this.nativeCountry = nativeCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

}


