package Kinopoisk.api.data.CinemaAssociation.Film;

import Kinopoisk.api.data.Person.Person;

import java.util.ArrayList;

public class Cast {
    private ArrayList<Role> foreground;
    private ArrayList<Role> supporting;

    public Cast(ArrayList<Role> foreground, ArrayList<Role> supporting) {
        this.foreground = foreground;
        this.supporting = supporting;
    }

    public Cast() {
        supporting = new ArrayList<>();
        foreground = new ArrayList<>();
    }

    public ArrayList<Role> getForeground(int index) {
        return new ArrayList<Role>(this.foreground);
    }

    public ArrayList<Role> getSupporting() {
        return new ArrayList<Role>(this.supporting);
    }

    public void addForeground(Person person, String role){
        this.foreground.add(new Role(person, role));
    }

    public void addSupporting(Person person, String role){
        this.supporting.add(new Role(person, role));
    }
}
