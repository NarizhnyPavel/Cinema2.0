package Kinopoisk.api.data.CinemaAssociation.Film;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;

/**
 * Класс, описывающий дату выхода фильма со свойствами <b></b> и <b>описание</b>.
 * @author Narizhny Pavel
 * @version 1.0
 */
public class ReleaseDate implements Serializable {
    /** Поле числового идентификатора объекта*/
    private int id;
    /** Поле текстового значения даты выхода фильма в мире*/
    private String world;
    /** Поле текстового значения даты выхода фильма в России*/
    private String rus;

    /**
     * Конструктор - создание нового объекта
     */
    @Contract(pure = true)
    public ReleaseDate() {
    }

    /**
     * Конструктор - создание нового объекта со свойствами
     * @param world - дата выхода в мире
     * @param rus - дата выхода в России
     */
    public ReleaseDate(String world, String rus) {
        this.world = world;
        this.rus = rus;
    }

    /**
     * Конструктор - создание нового объекта со свойствами
     * @param id - идентификатор объекта
     * @param world - дата выхода в мире
     * @param rus - дата выхода в России
     */
    public ReleaseDate(int id, String world, String rus) {
        this.id = id;
        this.world = world;
        this.rus = rus;
    }

    /**
     * Функция получения значения поля {@link ReleaseDate#id}
     * @return возвращает идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Процедура определения поля {@link ReleaseDate#id}
     * @param id - значение идентификатора
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Процедура определения поля {@link ReleaseDate#world}
     * @param world - дата выхода в мире
     */
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * Процедура определения поля {@link ReleaseDate#rus}
     * @param rus - дата выхода в России
     */
    public void setRus(String rus) {
        this.rus = rus;
    }

    /**
     * Функция получения объединённого значения полей
     * {@link ReleaseDate#world}, {@link ReleaseDate#rus}
     * в формате "в мире: <b>world</b> в России: <b>rus</b>;
     * @return возвращает возрастное ограничение
     */
    public String getDate() {
        return "в мире: " + world + " в России: " + rus;
    }


    /**
     * Функция получения значения поля {@link ReleaseDate#world}
     * @return возвращает дату выхода в мире
     */
    public String getWorld() {
        return world;
    }

    /**
     * Функция получения значения поля {@link ReleaseDate#rus}
     * @return возвращает дату выхода в России
     */
    public String getRus() {
        return rus;
    }
}
