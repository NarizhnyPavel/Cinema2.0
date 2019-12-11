package Kinopoisk.client.DataMethods.Person;

import Kinopoisk.api.data.Country.Country;
import Kinopoisk.api.data.Person.Person;
import Kinopoisk.api.data.Person.Profession;
import Kinopoisk.client.connection.ConnectionManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывабщий методы по работе с объектами класса {@link Person}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Persons {
    private static Persons instance;

    private Persons() {

    }

    public static synchronized Persons getInstance() {
        if(instance == null){
            instance = new Persons();
        }
        return instance;
    }

    private List<Person> getPersons() {
        return ConnectionManager.getInstance().getDataService().getPersons();
    }

    public Person getPerson(String name){
        return ConnectionManager.getInstance().getDataService().getPerson(name);
    }
    

    public void addPerson(String name, Profession prof, String birthDate, Country birthCountry){
        ConnectionManager.getInstance().getDataService().createPerson(new Person(name, prof, birthDate, birthCountry));
    }

    public void removePerson(String name){
        Person person = ConnectionManager.getInstance().getDataService().getPerson(name);
        if (person != null) {
            String mess = "персону " + person.getName();
            int answer = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить " + mess + " ?");
            if (answer == 0) {
                ConnectionManager.getInstance().getDataService().deletePerson(person);
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Персона не найдена в списке!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public int size(){
        return getPersons().size();
    }
    
    public List<Person> getDirectors(){
        return ConnectionManager.getInstance().getDataService().getPersonsWho(Professions.getInstance().getProfession(1));
    }

    public int getDirectorIndex(Person director){
        ArrayList<Person> directors = (ArrayList<Person>)getDirectors();
        for(int i = 0; i < directors.size(); i++)
            if (directors.get(i).getId() == director.getId())
                return i;
        return -1;
    }

    public List<Person> getWriters() {
        return ConnectionManager.getInstance().getDataService().getPersonsWho(Professions.getInstance().getProfession(2));
    }

    public int getWriterIndex(Person writer){
        ArrayList<Person> writers = (ArrayList<Person>)getWriters();
        for(int i = 0; i < writers.size(); i++)
            if (writers.get(i).getId() == writer.getId())
                return i;
        return -1;
    }
}
