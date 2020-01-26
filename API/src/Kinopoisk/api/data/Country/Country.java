package Kinopoisk.api.data.Country;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс, описывающий страну
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Country implements Serializable {
    /** Поле числового идентификатора*/
    private int id;
    /** Поле названия страны*/
    private String name;


    public Country(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.name = resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

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
}
