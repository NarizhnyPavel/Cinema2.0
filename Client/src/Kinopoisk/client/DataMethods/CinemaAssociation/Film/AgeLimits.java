package Kinopoisk.client.DataMethods.CinemaAssociation.Film;

import Kinopoisk.api.data.CinemaAssociation.Film.AgeLimit;
import Kinopoisk.client.connection.ConnectionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgeLimits implements Serializable {
    private static AgeLimits instance;

    private AgeLimits() {
    }

    public static synchronized AgeLimits getInstance() {
        if(instance == null){
            instance = new AgeLimits();
        }
        return instance;
    }

    public List<AgeLimit> getAgeLimits() {
        return ConnectionManager.getInstance().getDataService().getAgeLimits();
    }

    public AgeLimit getAgeLimit(int index) {
        return ConnectionManager.getInstance().getDataService().getAgeLimits().get(index - 1);
    }

    public int getAgeLimitIndex(AgeLimit ageLimit) {
        ArrayList<AgeLimit> ageLimits = (ArrayList<AgeLimit>) getAgeLimits();
        for (int i = 0; i < ageLimits.size(); i++)
            if (ageLimits.get(i).getId() == ageLimit.getId())
                return i;
        return -1;
    }
}
