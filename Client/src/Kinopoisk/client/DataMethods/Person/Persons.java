package Kinopoisk.client.DataMethods.Person;

import Kinopoisk.api.data.Country.Country;
import Kinopoisk.api.data.Person.Person;
import Kinopoisk.api.data.Person.Profession;
import Kinopoisk.client.connection.ConnectionManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Persons {
    //private ArrayList<Person> persons;
    private static Persons instance;
    /*private static List<Person> directors;
    private static List<Person> writers;
*/
    private Persons() {
        /*directors = new ArrayList<Person>();*/

        /*if (getPersons() == null || getPersons().size() == 1){
            addPerson("Даррен Аронофски", Professions.DIRECTOR, "12.02.1969", Countries.getInstance().getCountry("США"));
            addPerson("Крис Коламбус", Professions.DIRECTOR, "10.09.1958", Countries.getInstance().getCountry("США"));
            addPerson("Стивен Кловз", Professions.WRITER, "18.03.1960", Countries.getInstance().getCountry("США"));
            addPerson("Дж.К. Роулинг", Professions.WRITER, "31.07.1965", Countries.getInstance().getCountry("Великобритания"));
            addPerson("Дэниэл Рэдклифф", Professions.ACTOR, "23.07.1989", Countries.getInstance().getCountry("Великобритания"));
            addPerson("Руперт Гринт", Professions.ACTOR, "24.08.1988", Countries.getInstance().getCountry("Великобритания"));
            addPerson("Эмма Уотсон", Professions.ACTRESS, "15.04.1990", Countries.getInstance().getCountry("Франция"));
            addPerson("Ричард Харрис", Professions.ACTOR, "1.10.1930", Countries.getInstance().getCountry("Ирландия"));
            addPerson("Алан Рикман", Professions.ACTOR, "21.02.1946", Countries.getInstance().getCountry("Великобритания"));
            addPerson("Мэгги Смит", Professions.ACTRESS, "15.04.1990", Countries.getInstance().getCountry("Великобритания"));
            addPerson("Том Фелтон", Professions.ACTOR, "22.29.1987", Countries.getInstance().getCountry("Великобритания"));
            addPerson("Робби Колтрейн", Professions.ACTOR, "30.023.1950", Countries.getInstance().getCountry("Великобритания"));
            addPerson(" Жауме Кольет-Серра", Professions.DIRECTOR, " 23.03.1974", Countries.getInstance().getCountry("США"));
            addPerson("Байрон Уиллингер", Professions.WRITER, null, Countries.getInstance().getCountry("США"));
            addPerson("Фил ДиБлейси", Professions.WRITER, null, Countries.getInstance().getCountry("США"));
            addPerson("Райан Ингл", Professions.WRITER, null, Countries.getInstance().getCountry("США"));
            addPerson("Лиам Нисон", Professions.ACTOR, "07.06.1952", Countries.getInstance().getCountry("Франция"));
            addPerson("Патрик Уилсон", Professions.ACTOR, "03.06.1973", Countries.getInstance().getCountry("США"));
            addPerson("Вера Фармига", Professions.ACTRESS, "06.08.1973", Countries.getInstance().getCountry("Испания"));
            addPerson("Сэм Нил", Professions.ACTOR, "14.09.1947", Countries.getInstance().getCountry("Великобритания"));
            addPerson("Джонатан Бэнкс ", Professions.ACTOR, "31.01.1947", Countries.getInstance().getCountry("США"));
            addPerson("Элизабет Макговерн ", Professions.ACTRESS, "18.07.1961", Countries.getInstance().getCountry("США"));
            addPerson("Киллиан Скотт", Professions.ACTOR, "06.12.1985", Countries.getInstance().getCountry("Великобритания"));
            addPerson("Шазад Латиф ", Professions.ACTOR, "10.07.1988", Countries.getInstance().getCountry("Испания"));
            addPerson("Альфонсо Куарон", Professions.DIRECTOR, "28.11.1961", Countries.getInstance().getCountry(""));
            addPerson("Стивен Кловз", Professions.WRITER, "18.03.1960", Countries.getInstance().getCountry(""));
            addPerson("Дж.К. Роулинг", Professions.WRITER, "31.07.1965", Countries.getInstance().getCountry(""));
            addPerson("Дэниэл Рэдклифф", Professions.ACTOR, "23.07.1989", Countries.getInstance().getCountry(""));
            addPerson("Руперт Гринт", Professions.ACTOR, "24.08.1988", Countries.getInstance().getCountry(""));
            addPerson("Эмма Уотсон", Professions.ACTRESS, "15.04.1990", Countries.getInstance().getCountry(""));
            addPerson("Робби Колтрейн", Professions.ACTOR, "30.03.1950", Countries.getInstance().getCountry(""));
            addPerson("Том Фелтон", Professions.ACTOR, "22.09.1987", Countries.getInstance().getCountry(""));
            addPerson("Гари Олдман", Professions.ACTOR, "21.03.1958", Countries.getInstance().getCountry(""));
            addPerson("Дэвид Тьюлис", Professions.ACTOR, "20.03.1963", Countries.getInstance().getCountry(""));
        }*/
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
