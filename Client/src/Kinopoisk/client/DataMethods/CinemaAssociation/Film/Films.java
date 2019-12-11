package Kinopoisk.client.DataMethods.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.Film.AgeLimit;
import Kinopoisk.api.data.CinemaAssociation.Film.Film;
import Kinopoisk.api.data.CinemaAssociation.Film.Genre;
import Kinopoisk.api.data.CinemaAssociation.Film.ReleaseDate;
import Kinopoisk.api.data.CinemaStudio.CinemaStudio;
import Kinopoisk.api.data.CinemaAssociation.*;
import Kinopoisk.api.data.Country.Country;
import Kinopoisk.api.data.Person.Person;
import Kinopoisk.client.connection.ConnectionManager;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывабщий методы по работе с объектами класса {@link Film}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Films implements Serializable {
    private static Films instance=null;

    private Films() {

    }

    public static Films getInstance() {
        if(instance == null){
            instance = new Films();
        }
        return instance;
    }

    public List<Film> getFilms(){
        return ConnectionManager.getInstance().getDataService().getFilms();
    }

    public List<Film> getFilmsOf(CinemaAssociation association) {
        return (List<Film>) ConnectionManager.getInstance().getDataService().getFilmsOf(association);
    }

    public void addFilm(CinemaAssociation cinemaAss, String name, ReleaseDate releaseDate, Person dir, Person writer, Country country, CinemaStudio studio, AgeLimit ageLimit, Genre genre){
        ConnectionManager.getInstance().getDataService().createFilm(new Film(cinemaAss, name, releaseDate, dir, writer, country, studio, ageLimit, genre));
    }

    public void updateFilm(Film film){
        ConnectionManager.getInstance().getDataService().updateFilm(film);
    }

    public void removeFilm(Film film){
        if (film != null) {
            String mess = "фильм " + film.getName();
            int answer = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить " + mess + "?");
            if (answer == 0) {
                ConnectionManager.getInstance().getDataService().deleteFilm(film);
            }
        }
    }
}
