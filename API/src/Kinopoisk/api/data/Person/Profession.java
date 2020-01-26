package Kinopoisk.api.data.Person;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Profession(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.name = resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
