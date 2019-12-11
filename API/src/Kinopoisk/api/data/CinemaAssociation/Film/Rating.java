package Kinopoisk.api.data.CinemaAssociation.Film;

/**
 * Класс, описывающий рейтинг фильма
 * со свойствами <b>рейтинг на сайте IMDb</b>
 * <b>рейтинг на сайте Кинопоиск</b> и <b>рейтинг на нашем сайте</b>.
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Rating{
    /** Поле числового рейтинга на сайте IMDb*/
    private double kp; // rate if our rival
    /** Поле числового рейтинга на сайте Кинопоиск*/
    private double kp2; // our own rate
    /** Поле числового рейтинга в нашей системе*/
    private double imdb;

    /**
     * Конструтор нового объекта со свойствами
     * @param imdb - значение рейтинга на сайте IMDb
     * @param kp - значение рейтинга на сайте Коинопоиск
     * @param kp2 - - значение рейтинга на нашем сайте
     */
    public Rating(double imdb, double kp, double kp2) {
        this.imdb = imdb;
        this.kp = kp;
        this.kp2 = kp2;
    }
}