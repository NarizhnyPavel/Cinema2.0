package Kinopoisk.api.data.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.CinemaAssociation;
import Kinopoisk.api.data.CinemaStudio.CinemaStudio;
import Kinopoisk.api.data.Country.Country;
import Kinopoisk.api.data.Person.Person;

import java.io.Serializable;

public class Film implements Serializable {
    private long id;
    private CinemaAssociation cinemaAss;
    private String name;
    private ReleaseDate releaseDate; //link on another class
    private Person dir;
    private Person writer;
    private Cast cast;
    private Rating rate;
    private Genre genre;
    private Country country;
    private AgeLimit ageLim;
    private CinemaStudio studio;
    private String discript;
    private Review rev;
    private long money; // system may generate string from integer 1000000000 -> "1 млрд"
    private String trailer; //here will be link on website
    private String poster; //here will be link on photo. at first version name of file
    private long duration;
    private int countSeries;
    private long budget; // 1250000 -> "1.25 млн"
    private long count_of_viewers;

    public Film() {
    }

    public void setAgeLim(AgeLimit ageLim) {
        this.ageLim = ageLim;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Film (CinemaAssociation ass, String name, ReleaseDate date, Person dir, Genre genre, Country country, AgeLimit ageLim){
        this.cinemaAss = ass;
        this.name = name;
        this.releaseDate = date;
        this.dir = dir;
        this.genre = genre;
        this.country = country;
        this.ageLim = ageLim;
    }

    public Person getDir() {
        return dir;
    }

    public Film(long id, CinemaAssociation cinemaAss, String name, ReleaseDate releaseDate, Person dir, Person writer, Country country, CinemaStudio studio) {
        this.id = id;
        this.cinemaAss = cinemaAss;
        this.name = name;
        this.releaseDate = releaseDate;
        this.dir = dir;
        this.writer = writer;
        this.country = country;
        this.studio = studio;
    }

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


    public void setCinemaAss(CinemaAssociation cinemaAss) {
        this.cinemaAss = cinemaAss;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Person getWriter() {
        return writer;
    }

    public CinemaStudio getStudio() {
        return studio;
    }

    public void setDir(Person dir) {
        this.dir = dir;
    }

    public void setWriter(Person writer) {
        this.writer = writer;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setStudio(CinemaStudio studio) {
        this.studio = studio;
    }

    public Film(CinemaAssociation cinemaAss, String name, ReleaseDate releaseDate, Person dir, Country country, AgeLimit ageLim) {
        this.cinemaAss = cinemaAss;
        this.name = name;
        this.releaseDate = releaseDate;
        this.dir = dir;
        this.country = country;
        this.ageLim = ageLim;
    }

    public String getName() {
        return name;
    }

    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public Country getCountry() {
        return country;
    }

    public AgeLimit getAgeLim() {
        return ageLim;
    }

    public CinemaAssociation getCinemaAss(){return cinemaAss;}
}
