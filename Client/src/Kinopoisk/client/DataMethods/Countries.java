package Kinopoisk.client.DataMethods;

import Kinopoisk.api.data.Country.Country;
import Kinopoisk.client.connection.ConnectionManager;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Countries implements Serializable {
    private static Countries instance;

    private Countries() {
        if (getCountries() == null || getCountries().size() == 0) {
            getCountry("Италия");
            getCountry("США");
            getCountry("Испания");
        }
    }

    public static synchronized Countries getInstance() {
        if(instance == null){
            instance = new Countries();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public List<Country> getCountries() {
        return ConnectionManager.getInstance().getDataService().getCountries();
    }

    /**
     *
     * @param name
     * @return
     */
    public Country getCountry(String name){
        Country country = ConnectionManager.getInstance().getDataService().getCountry(name);
        if (country != null){
            return country;
        } else {
            int id = ConnectionManager.getInstance().getDataService().createCountry(name);
            country = new Country(id, name);
            return country;
        }
    }
}

