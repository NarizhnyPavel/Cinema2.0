package Kinopoisk.client.DataMethods.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.Film.Arcticle;

import java.util.ArrayList;

public class Arcticles {
    private static Arcticles instance;
    private ArrayList<Arcticle> arcticles;


    private Arcticles() {
        arcticles = new ArrayList<>();
        arcticles.add(new Arcticle("https://4loto.ru/content/15684", "В чём смысл фильма, который все смотрели и никто ничего не понял"));
    }

    public static synchronized Arcticles getInstance() {
        if(instance == null){
            instance = new Arcticles();
        }
        return instance;
    }

    public ArrayList<Arcticle> getArcticles() {
        return new ArrayList<Arcticle>(arcticles);
    }

    public void addArcticle(String link, String text){
        this.arcticles.add(new Arcticle(link, text));
    }
}