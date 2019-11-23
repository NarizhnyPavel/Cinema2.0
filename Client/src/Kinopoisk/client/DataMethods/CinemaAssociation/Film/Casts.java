package Kinopoisk.client.DataMethods.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.Film.Cast;
import Kinopoisk.api.data.CinemaAssociation.Film.Role;

import java.util.ArrayList;

public class Casts {
    private ArrayList<Cast> casts;

    private static Cast instance;

    private Casts() {
        casts = new ArrayList<>();

       // casts.add(new Cast(/*роли первого плана*/new ArrayList<Role>(new Person ()),
               // /*роли второго плана*/new ArrayList<Role>(new Person ())));
    }

    public static synchronized Cast getInstance() {
        if(instance == null){
            instance = new Cast();
        }
        return instance;
    }

    public ArrayList<Cast> getCasts() {
        return new ArrayList<Cast>(casts);
    }

    public void addCast(ArrayList<Role> foregr, ArrayList<Role> surr){
        this.casts.add(new Cast(foregr, surr));
    }
}
