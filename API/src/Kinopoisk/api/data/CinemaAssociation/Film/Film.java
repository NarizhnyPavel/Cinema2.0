package Kinopoisk.api.data.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.CinemaAssociation;
import Kinopoisk.api.data.CinemaStudio.CinemaStudio;
import Kinopoisk.api.data.Country.Country;
import Kinopoisk.api.data.Person.Person;
import Kinopoisk.api.services.ImagesDownloader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Класс, описывающий фильм
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Film implements Serializable {
    /** Поле числового идентификатора*/
    private long id;
    /** Поле родительского кинообъединения {@link CinemaAssociation}*/
    private CinemaAssociation cinemaAss;
    /** Поле строкового имени*/
    private String name;
    /** Поле даты выхода картины {@link ReleaseDate}*/
    private ReleaseDate releaseDate;
    /** Поле режиссёра {@link Person}*/
    private Person dir;
    /** Поле сценариста {@link Person}*/
    private Person writer;
    /** Поле рейтинга {@link Rating}*/
    private Rating rate;
    /** Поле жанра {@link Genre}*/
    private Genre genre;
    /** Поле страны {@link Country}*/
    private Country country;
    /** Поле возрастного ограничения {@link AgeLimit}*/
    private AgeLimit ageLim;
    /** Поле киностудии {@link CinemaStudio}*/
    private CinemaStudio studio;
    /** Поле подробного описания */
    private String discript;
    /** Поле бюджета */
    private long money; // system may generate string from integer 1000000000 -> "1 млрд"
    /** Поле ссылки на трейлер */
    private String trailer; //here will be link on website
    /** Поле ссылки на постер */
    private byte[] poster;
    /** Поле продолжительности фильма */
    private long duration;
    /** Поле количества серий. Применяется в случае описания сезона сериала */
    private int countSeries;
    /** Поле бюджета */
    private long budget; // 1250000 -> "1.25 млн"
    /** Поле количества зрителей */
    private long count_of_viewers;

    /**
     * Конструктор - создание нового объекта
     */
    public Film() {
    }

    /**
     * Конструктор - создание нового объекта со свойствами
     * @param cinemaAss - кинообъединение {@link CinemaAssociation}
     * @param name - имя фильма
     * @param releaseDate - дата выхода {@link ReleaseDate}
     * @param dir - режиссёр {@link Person}
     * @param writer - сценарист {@link Person}
     * @param country - страна {@link Country}
     * @param studio - киностудия {@link CinemaStudio}
     * @param ageLimit - возрастное ограничение {@link AgeLimit}
     * @param genre - жанр {@link Genre}
     */
    public Film(CinemaAssociation cinemaAss, String name, ReleaseDate releaseDate, Person dir, Person writer, Country country, CinemaStudio studio, AgeLimit ageLimit, Genre genre) {
        this.cinemaAss = cinemaAss;
        this.name = name;
        this.releaseDate = releaseDate;
        this.dir = dir;
        this.writer = writer;
        this.country = country;
        this.studio = studio;
        this.ageLim = ageLimit;
        this.genre = genre;
    }

    /**
     * Процедура определения поля {@link Film#ageLim}
     * @param ageLim - возрастное ограничение {@link AgeLimit}
     */
    public void setAgeLim(AgeLimit ageLim) {
        this.ageLim = ageLim;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Процедура определения поля {@link Film#ageLim}
     * @param id - возрастное ограничение {@link AgeLimit}
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Процедура определения поля {@link Film#genre}
     * @param genre - жанр
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Image getPoster() {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(poster));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return resize(bufferedImage, 400, 250);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    /**
     * Процедура определения поля {@link Film#poster}
     * @param poster - ссылка на постер
     */
    public void setPoster(byte[] poster) {
        this.poster = poster;
    }

    public Person getDir() {
        return dir;
    }

    /**
     * Процедура определения поля {@link Film#ageLim}
     * @param cinemaAss - кинообъединение
     */
    public void setCinemaAss(CinemaAssociation cinemaAss) {
        this.cinemaAss = cinemaAss;
    }

    /**
     * Процедура определения поля {@link Film#name}
     * @param name - имя фильма
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Процедура определения поля {@link Film#releaseDate}
     * @param releaseDate - дата выхода
     */
    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Функция получения значения поля {@link Film#writer}
     * @return - сценарист
     */
    public Person getWriter() {
        return writer;
    }

    /**
     * Функция получения значения поля {@link Film#studio}
     * @return - киностудия
     */
    public CinemaStudio getStudio() {
        return studio;
    }

    /**
     * Процедура определения поля {@link Film#dir}
     * @param dir - режиссёр
     */
    public void setDir(Person dir) {
        this.dir = dir;
    }

    /**
     * Процедура определения поля {@link Film#writer}
     * @param writer - сценарист
     */
    public void setWriter(Person writer) {
        this.writer = writer;
    }

    /**
     * Процедура определения поля {@link Film#country}
     * @param country - страна
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Процедура определения поля {@link Film#studio}
     * @param studio - киностудия
     */
    public void setStudio(CinemaStudio studio) {
        this.studio = studio;
    }

    /**
     * Функция получения значения поля {@link Film#name}
     * @return - название фильма
     */
    public String getName() {
        return name;
    }

    /**
     * Функция получения значения поля {@link Film#releaseDate}
     * @return - дата выхода
     */
    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Функция получения значения поля {@link Film#genre}
     * @return - жанр
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Функция получения значения поля {@link Film#country}
     * @return - страна происхожденя
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Функция получения значения поля {@link Film#ageLim}
     * @return - возрастное ограничение
     */
    public AgeLimit getAgeLim() {
        return ageLim;
    }

    /**
     * Функция получения значения поля {@link Film#cinemaAss}
     * @return - кинообъединение
     */
    public CinemaAssociation getCinemaAss(){return cinemaAss;}


}
