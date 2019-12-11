package Kinopoisk.client.DataMethods.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.Film.Genre;
import Kinopoisk.client.connection.ConnectionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывабщий методы по работе с объектами класса {@link Genre}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Genres implements Serializable {
    private static Genres instance;

    private Genres() {
    }

    public static synchronized Genres getInstance() {
        if(instance == null){
            instance = new Genres();
        }
        return instance;
    }

    public List<Genre> getGenres() {
        return ConnectionManager.getInstance().getDataService().getGenres();
    }

    public Genre getGenre(int index) {
        return ConnectionManager.getInstance().getDataService().getGenres().get(index - 1);
    }

    public int getGenreIndex(Genre genre) {
        ArrayList<Genre> genres = (ArrayList<Genre>) getGenres();
        for (int i = 0; i < genres.size(); i++)
            if (genres.get(i).getId() == genre.getId())
                return i;
        return -1;
    }
}
