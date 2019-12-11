package Kinopoisk.client.DataMethods.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.Film.Rating;

import java.util.ArrayList;

/**
 * Класс, описывабщий методы по работе с объектами класса {@link Rating}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Ratings {
    private static Ratings instance;
    private ArrayList<Rating> ratings;

    private Ratings() {
        ratings = new ArrayList<>();
        ratings.add(new Rating(6.6, 7.1, 0.0));
    }

    public static synchronized Ratings getInstance() {
        if(instance == null){
            instance = new Ratings();
        }
        return instance;
    }

    public ArrayList<Rating> getRatings() {
        return new ArrayList<Rating>(ratings);
    }

    public void addReview(int imdb, int kp, int kp2){
        this.ratings.add(new Rating(imdb, kp, kp2));
    }
}
