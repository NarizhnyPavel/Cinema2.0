package Kinopoisk.client.DataMethods.CinemaAssociation;

import Kinopoisk.api.data.CinemaAssociation.CinemaAssociation;
import Kinopoisk.api.data.User.User;
import Kinopoisk.client.connection.ConnectionManager;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывабщий методы по работе с объектами класса {@link CinemaAssociation}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class CinemaAssociations {
    private static CinemaAssociations instance;

    private CinemaAssociations() {

    }

    public static synchronized CinemaAssociations getInstance() {
        if(instance == null){
            instance = new CinemaAssociations();
        }
        return instance;
    }

    public List<CinemaAssociation> getCinemaAssociations() {
        return ConnectionManager.getInstance().getDataService().getAssociations();
    }

    public void addAssociation(CinemaAssociation ass){
        ConnectionManager.getInstance().getDataService().createAssociation(ass);
    }

    public ArrayList<CinemaAssociation> getListOf (User mod){
        List<CinemaAssociation> associations = ConnectionManager.getInstance().getDataService().getAssociations();
        ArrayList<CinemaAssociation> list = new ArrayList<>();
        for (int i = 0; i < associations.size(); i++) {
            if (associations.get(i).getMod().getId() == mod.getId())
                list.add(associations.get(i));
        }
        return list;
    }

    public CinemaAssociation getAssociation(String name){
        return ConnectionManager.getInstance().getDataService().getAssociation(name);
    }

    public void removeAssociation(String name){
        CinemaAssociation association = ConnectionManager.getInstance().getDataService().getAssociation(name);
        if (association != null) {
            String mess = "кинообъединение " + association.getName();
            int answer = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить " + mess + " ?");
            if (answer == 0) {
                ConnectionManager.getInstance().getDataService().deleteAssociation(association);
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Кинообъединение не найдено в списке!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public void removeAssociation(CinemaAssociation association) {
        ConnectionManager.getInstance().getDataService().deleteAssociation(association);
    }
}
