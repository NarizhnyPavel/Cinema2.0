package Kinopoisk.api.data.CinemaAssociation.Film;

public class Rating{
    private double imdb;
    private double kp; // rate if our rival
    private double kp2; // our own rate

    public Rating(double imdb, double kp, double kp2) {
        this.imdb = imdb;
        this.kp = kp;
        this.kp2 = kp2;
    }
}