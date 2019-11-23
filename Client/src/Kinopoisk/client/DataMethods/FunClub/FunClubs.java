package Kinopoisk.client.DataMethods.FunClub;

import Kinopoisk.api.data.FunClub.FunClub;

import java.util.ArrayList;

public class FunClubs {
    private static FunClubs instance;
    private ArrayList<FunClub> funClubs;

    private FunClubs() {
        funClubs = new ArrayList<>();
        funClubs.add(new FunClub("Hurry Potter lovers", "www"));
        funClubs.add(new FunClub("Marvel lovers", "www"));
    }

    public static synchronized FunClubs getInstance() {
        if(instance==null){
            instance = new FunClubs();
        }
        return instance;
    }

    public ArrayList<FunClub> getFunClubs() {
        return new ArrayList<FunClub>(funClubs);
    }

    public void addFunClub(FunClub funClub){
        funClubs.add(funClub);
    }
}
