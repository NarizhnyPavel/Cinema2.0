package Kinopoisk.api.data.CinemaAssociation.Film;

import java.io.Serializable;

/**
 * Класс, описывающий жанр фильма со свойством <b>имя</b>.
 * Объекты образуют непоплняемый список, хранящийся на БД
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Genre implements Serializable {
    /** Поле числового идентифика объекта*/
    private int id;
    /** Поле Наименования жанра*/
    private String name;

    /**
     * Конмтруктор - создание нового объекта
     */
    public Genre() { }

    /**
     * Конмтруктор - создание нового объекта c определённым значениями
     * @param genre - наименование жанра
     */
    public Genre(String genre) {
        this.name = genre;
    }

    /**
     * Функция получения значения поля {@link Genre#id}
     * @return возвращает индентифкатор объекта
     */
    public int getId() {
        return id;
    }

    /**
     * Процедура определения поля {@link Genre#id}
     * @param id - текстовое описание
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Процедура определения поля {@link Genre#name}
     * @param discript - наименование жанра
     */
    public void setDiscript(String discript) {
        this.name = discript;
    }

    /**
     * Функция получения значения поля {@link Genre#name}
     * @return возвращает наименование жанра
     */
    public String getDiscript() {
        return name;
    }
}
