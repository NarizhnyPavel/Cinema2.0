package Kinopoisk.api.data.CinemaAssociation;

import Kinopoisk.api.data.FunClub.FunClub;
import Kinopoisk.api.data.User.User;

import java.io.Serializable;
import java.util.Comparator;

public class CinemaAssociation implements Serializable {
    private TypeAssociation type;
    private int id;
    private String name;
    private User mod;
    private FunClub club = null; //for version 2.0

    public void setClub(FunClub club) {
        this.club = club;
    }

    public CinemaAssociation(int id, TypeAssociation type, String name, User mod) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.mod = mod;
    }

    public CinemaAssociation() {

    }

    public CinemaAssociation(TypeAssociation type, String name, User mod, FunClub club) {
        this.type = type;
        this.name = name;
        this.mod = mod;
        this.club = club;
    }

    public CinemaAssociation(TypeAssociation type, String name, User mod) {
        this.type = type;
        this.name = name;
        this.mod = mod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TypeAssociation getType() {
        return type;
    }

    public String getTypeString(){
        return type.getTypeString();
    }

    public void setType(TypeAssociation type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getMod() {
        return mod;
    }

    public FunClub getClub() {
        return club;
    }

    public void setMod(User mod) {
        this.mod = mod;
    }

    public static class Comparators {

        public static Comparator<CinemaAssociation> NAME = new Comparator<CinemaAssociation>() {
            @Override
            public int compare(CinemaAssociation o1, CinemaAssociation o2) {
                return o1.name.compareTo(o2.name);
            }
        };
    }
}
