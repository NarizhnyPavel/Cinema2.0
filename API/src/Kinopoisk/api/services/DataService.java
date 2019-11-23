package Kinopoisk.api.services;

import Kinopoisk.api.data.CinemaAssociation.CinemaAssociation;
import Kinopoisk.api.data.CinemaAssociation.Film.AgeLimit;
import Kinopoisk.api.data.CinemaAssociation.Film.Film;
import Kinopoisk.api.data.CinemaAssociation.Film.Genre;
import Kinopoisk.api.data.CinemaAssociation.Film.ReleaseDate;
import Kinopoisk.api.data.CinemaAssociation.TypeAssociation;
import Kinopoisk.api.data.CinemaStudio.CinemaStudio;
import Kinopoisk.api.data.Country.Country;
import Kinopoisk.api.data.FunClub.FunClub;
import Kinopoisk.api.data.Person.Person;
import Kinopoisk.api.data.Person.Profession;
import Kinopoisk.api.data.User.User;
import sun.java2d.pipe.AATileGenerator;

import java.sql.SQLException;
import java.util.List;

public interface DataService
{
    public List<CinemaAssociation> getAssociations();

    CinemaAssociation getAssociation(String name);

    public List<CinemaAssociation> getAssociationsOf(User moderator);

    void createAssociation(CinemaAssociation association);

    void deleteAssociation(CinemaAssociation association);

    void updateAssociation(CinemaAssociation association);

    public List<User> getModerators();

    public User getModerator(String name);

    void createModerator(User moderator) throws SQLException;

    public void deleteModerator(User moderator);

    public void updateModerator(User moderator);

    public List<User> getUsers();

    public User getUser(String name);

    void createUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    public User getSuperUser();

    public void updateSuperUser(User user);

    TypeAssociation getTypeAssociation (int index);

    public List<Person> getPersons();

    public List<Person> getPersonsWho(Profession profession);

    public Person getPerson(String name);

    int createPerson(Person person);

    public void deletePerson(Person person);

    public void updatePerson(Person person);

    public List<Film> getFilms();

    public Film getFilm(String name);

    public List<Film> getFilmsOf(CinemaAssociation association);

    public void createFilm(Film film);

    public void deleteFilm(Film film);

    public void updateFilm(Film film);

    public List<FunClub> getFunClubs();

    public FunClub getFunClub(String name);

    public FunClub getFunClub(int id);

    public void createFunClub(FunClub funClub);

    public void deleteFunClub(FunClub funClub);

    public void updateFunClub(FunClub funClub);

    public List<Country> getCountries();

    public Country getCountry(String name);

    public Country getCountry(int id);

    public int createCountry(String name);

    public List<CinemaStudio> getCinemaStudios();

    public CinemaStudio getCinemaStudio(String name);

    public CinemaStudio getCinemaStudio(int id);

    public int createCinemaStudio(CinemaStudio studio);

    public void deleteCinemaStudio(CinemaStudio studio);

    public void updateCinemaStudio(CinemaStudio studio);

    public List<ReleaseDate> getReleaseDates();

    public ReleaseDate getReleaseDate(int id);

    public int createReleaseDate(ReleaseDate releaseDate);

    public void updateReleaseDate(ReleaseDate releaseDate);

    public List<AgeLimit> getAgeLimits();

    public List<Genre> getGenres();

    public List<Profession> getProfessions();
}


