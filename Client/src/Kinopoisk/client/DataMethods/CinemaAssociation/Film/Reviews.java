package Kinopoisk.client.DataMethods.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.Film.Review;
import Kinopoisk.api.data.User.User;

import java.util.ArrayList;

public class Reviews {
    private static Reviews instance;
    private ArrayList<Review> reviews;

    private Reviews() {
        reviews = new ArrayList<>();
        //reviews.add(new Review());
    }

    public static synchronized Reviews getInstance() {
        if(instance == null){
            instance = new Reviews();
        }
        return instance;
    }

    public ArrayList<Review> getReviews() {
        return new ArrayList<Review>(reviews);
    }

    public void addReview(User author, int rate, String text){
        this.reviews.add(new Review(author, rate, text));
    }
}
