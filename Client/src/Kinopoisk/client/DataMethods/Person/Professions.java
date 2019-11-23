package Kinopoisk.client.DataMethods.Person;

import Kinopoisk.api.data.Person.Profession;
import Kinopoisk.client.connection.ConnectionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professions  implements Serializable {
    private static Professions instance;

    private Professions() {
    }

    public static synchronized Professions getInstance() {
        if(instance == null){
            instance = new Professions();
        }
        return instance;
    }

    public List<Profession> getProfessions() {
        return ConnectionManager.getInstance().getDataService().getProfessions();
    }

    public Profession getProfession(int index) {
        return ConnectionManager.getInstance().getDataService().getProfessions().get(index - 1);
    }

    public int getProfessionIndex(Profession genre) {
        ArrayList<Profession> professions = (ArrayList<Profession>) getProfessions();
        for (int i = 0; i < professions.size(); i++)
            if (professions.get(i).getId() == genre.getId())
                return i;
        return -1;
    }
}
