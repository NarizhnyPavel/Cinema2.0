package Kinopoisk.client.DataMethods;

import Kinopoisk.api.data.CinemaStudio.CinemaStudio;
import Kinopoisk.api.data.Country.Country;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Kinopoisk.api.services.DataService;
import Kinopoisk.client.DataMethods.*;
import Kinopoisk.client.connection.ConnectionManager;

public class CinemaStudios {
    private static CinemaStudios instance;

    private CinemaStudios() {
    }

    public static synchronized CinemaStudios getInstance() {
        if(instance == null){
            instance = new CinemaStudios();
        }
        return instance;
    }

    public void updateStudio(CinemaStudio studio){
        ConnectionManager.getInstance().getDataService().updateCinemaStudio(studio);
    }

    public List<CinemaStudio> getCinemaStudios() {
        return ConnectionManager.getInstance().getDataService().getCinemaStudios();
    }

    public CinemaStudio getCinemaStudio(int index) {
        return ConnectionManager.getInstance().getDataService().getCinemaStudios().get(index - 1);
    }

    public void addStudio(CinemaStudio studio){
        studio.setId(ConnectionManager.getInstance().getDataService().createCinemaStudio(studio));
    }

    public CinemaStudio getCinemaStudio(String name){
        CinemaStudio studio = ConnectionManager.getInstance().getDataService().getCinemaStudio(name);
        if (studio == null){
            JOptionPane.showMessageDialog(null, "Киностудия не найдена в списке!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            return null;
        } else {
            return studio;
        }
    }
    public int getStudioIndex(CinemaStudio studio) {
        ArrayList<CinemaStudio> studios = (ArrayList<CinemaStudio>) getCinemaStudios();
        for (int i = 0; i < studios.size(); i++)
            if (studios.get(i).getId() == studio.getId())
                return i;
        return -1;
    }
}
