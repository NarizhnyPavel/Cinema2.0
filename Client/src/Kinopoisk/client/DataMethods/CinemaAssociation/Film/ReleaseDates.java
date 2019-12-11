package Kinopoisk.client.DataMethods.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.Film.ReleaseDate;
import Kinopoisk.client.connection.ConnectionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывабщий методы по работе с объектами класса {@link ReleaseDate}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class ReleaseDates implements Serializable {
    private static ReleaseDates instance;

    private ReleaseDates() {
    }

    public static synchronized ReleaseDates getInstance() {
        if(instance == null){
            instance = new ReleaseDates();
        }
        return instance;
    }

    public List<ReleaseDate> getReleaseDates() {
        return ConnectionManager.getInstance().getDataService().getReleaseDates();
    }

    public ReleaseDate addReleaseDate(String world, String rus){
        ReleaseDate date = new ReleaseDate(world, rus);
        date.setId(ConnectionManager.getInstance().getDataService().createReleaseDate(date));
        return date;
    }

    public void updateReleaseDate(ReleaseDate releaseDate){
        ConnectionManager.getInstance().getDataService().updateReleaseDate(releaseDate);
    }
}
