package Kinopoisk.api.data.CinemaAssociation.Film;


import Kinopoisk.api.data.Person.Person;

public class Role {
    private Person person;
    private String role;

    public Role(Person person, String role) {
        this.person = person;
        this.role = role;
    }
}
