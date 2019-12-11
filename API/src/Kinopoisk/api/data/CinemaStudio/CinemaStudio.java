package Kinopoisk.api.data.CinemaStudio;

import Kinopoisk.api.data.Country.Country;

import java.io.Serializable;

/**
 * Класс, описывающий киностудию <br>
 * @author Narizhny Pavel
 * @version 1.0
 */
public class CinemaStudio implements Serializable {
    /** Поле числового идентификатора объекта*/
    private int id;
    /** Поле названия киностудии*/
    private String name;
    /** Поле даты создания кинообъединения*/
    private String createDate;
    /** Поле страны, к которой относится киностудия*/
    private Country nativeCountry;

    /**
     * Конструктор - создание нового объекта
     */
    public CinemaStudio() {
    }

    /**
     * Констурктор - создание нового объекта со свойствами
     * @param name - название киностудии
     * @param createDate - дата создания
     * @param nativeCountry - страна происхождения
     */
    public CinemaStudio(String name, String createDate, Country nativeCountry) {
        this.name = name;
        this.createDate = createDate;
        this.nativeCountry = nativeCountry;
    }

    /**
     * Процедура определения поля {@link CinemaStudio#name}
     * @param name - название киностудии
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения значения поля {@link CinemaStudio#createDate}
     * @return - дата создания
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Процедура определения поля {@link CinemaStudio#createDate}
     * @param createDate - дата создания
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Функция получения значения поля {@link CinemaStudio#nativeCountry}
     * @return - страна происхождения
     */
    public Country getNativeCountry() {
        return nativeCountry;
    }

    /**
     * Процедура определения поля {@link CinemaStudio#nativeCountry}
     * @param nativeCountry - страна происхождения
     */
    public void setNativeCountry(Country nativeCountry) {
        this.nativeCountry = nativeCountry;
    }

    /**
     * Функция получения значения поля {@link CinemaStudio#id}
     * @return - числовой идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Процедура определения поля {@link CinemaStudio#id}
     * @param id - числовой идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля {@link CinemaStudio#name}
     * @return - имя
     */
    public String getName() {
        return name;
    }

}


