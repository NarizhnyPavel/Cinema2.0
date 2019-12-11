package Kinopoisk.api.data.CinemaAssociation.Film;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;

/**
 * Класс, описывающий возрастное ограничение фильма со свойствами <b>рекомендуемый возраст для просмотра</b> и <b>описание</b>.
 * @author Narizhny Pavel
 * @version 1.0
 */
public class AgeLimit implements Serializable {
    /** Поле числового идентифика объекта*/
    private int id;
    /** Поле числового ограничения минимального возраста для просмотра*/
    private int limit;
    /** Поле дополнительного текстового описания ограничения*/
    private String discript;

    /**
     * Конструктор - создание нового объека
     */
    @Contract(pure = true)
    public AgeLimit() {
    }

    /**
     * Функция получения значения поля {@link AgeLimit#id}
     * @return возвращает индентифкатор объекта
     */
    public int getId() {
        return id;
    }

    /**
     * Процедура определения поля {@link AgeLimit#id}
     * @param id - текстовое описание
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля {@link AgeLimit#limit}
     * @return возвращает возрастное ограничение
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Процедура определения поля {@link AgeLimit#limit}
     * @param limit - числовое значение ограничения
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Функция получения значения поля {@link AgeLimit#discript}
     * @return возвращает дополнительное описание
     */
    public String getDiscript() {
        return discript;
    }

    /**
     * Процедура определения поля {@link AgeLimit#discript}
     * @param discript - текстовое описание
     */
    public void setDiscript(String discript) {
        this.discript = discript;
    }
}