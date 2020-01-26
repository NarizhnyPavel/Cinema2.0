package Kinopoisk.api.data.CinemaAssociation;

import Kinopoisk.api.data.User.User;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс, описывающий кинообъединение, группирующее фильмы, и обладает свойствами:
 * <b>тип</b>, <b>имя</b>, <b>модератор</b>, <b></b>.
 * @author Narizhny Pavel
 * @version 1.0
 */
public class CinemaAssociation implements Serializable {
    /** Поле числового идентифика объекта*/
    private int id;
    /** Поле типа объединения
     * @see TypeAssociation
     * */
    private TypeAssociation type;
    /** Поле имени объединения*/
    private String name;
    /** Поле курирующего пользователь с правами модератора
     * @see User
     * */
    private User mod;

    /**
     * Конструктор - создание нового объека
     */
    public CinemaAssociation(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.type = new TypeAssociation(resultSet.getString ( "type" ));
            this.name = resultSet.getString ( "name" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Конструктор - создание нового объека со свойствами
     * @param id - числового идентифика
     * @param type - тип объединения
     * @see TypeAssociation
     * @param name - имя объединения
     * @param mod - курирующий пользователь с правами модератора
     * @see User
     */
    public CinemaAssociation(int id, TypeAssociation type, String name, User mod) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.mod = mod;
    }

    /**
     * Конструктор - создание нового объека со свойствами
     * @param type - тип объединения
     * @see TypeAssociation
     * @param name - имя объединения
     * @param mod - курирующий пользователь с правами модератора
     * @see User
     */
    public CinemaAssociation(TypeAssociation type, String name, User mod) {
        this.type = type;
        this.name = name;
        this.mod = mod;
    }

    /**
     * Процедура определения поля {@link CinemaAssociation#id}
     * @param id - числовой идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля {@link CinemaAssociation#id}
     * @return числовой идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Функция получения значения поля {@link CinemaAssociation#name}
     * @return имя кинообъединения
     */
    public String getName() {
        return name;
    }

    /**
     * Функция получения значения поля {@link CinemaAssociation#type}
     * @return тип объединения
     * @see TypeAssociation
     */
    public TypeAssociation getType() {
        return type;
    }

    /**
     * Процедура определения поля {@link CinemaAssociation#type}
     * @param type - тип кинобъединения
     * @see TypeAssociation
     */
    public void setType(TypeAssociation type) {
        this.type = type;
    }

    /**
     * Процедура определения поля {@link CinemaAssociation#name}
     * @param name - имя кинообъединения
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Процедура определения поля {@link CinemaAssociation#mod}
     * @return - пользователь с правами модератора
     */
    public User getMod() {
        return mod;
    }

    /**
     * Процедура определения поля {@link CinemaAssociation#mod}
     * @param mod - пользователь с правами модератора
     */
    public void setMod(User mod) {
        this.mod = mod;
    }

}
