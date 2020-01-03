package Kinopoisk.client.DataMethods.User;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Kinopoisk.api.data.User.User;
import Kinopoisk.client.DataMethods.CinemaAssociation.CinemaAssociations;
import Kinopoisk.client.connection.ConnectionManager;

/**
 * Класс, описывающий методы по работе с объектами класса {@link User}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class Cerberus {
    /** текущий авторизованный пользователь*/
    private static User curUser;
    private static String seanceId;
    private static Cerberus instance;

    private Cerberus()  {

    }

    public static synchronized Cerberus getInstance()  {
        if(instance==null){
            instance = new Cerberus();
        }
        return instance;
    }

    public  String getSeanceId() {
        return seanceId;
    }

    public  void setSeanceId(String seanceId) {
        Cerberus.seanceId = seanceId;
    }

    public  User getCurUser() {
        return curUser;
    }

    public  void setCurUser(User curUser) {
        Cerberus.curUser = curUser;
    }

    public void updateSessionOf(User user){
        user.updateSession();
        updateUser(user);
    }

    public void updateUser(User user){
        ConnectionManager.getInstance().getDataService().updateUser(user);
    }

    public  List<User> getModerators() {
        List<User> moderators = ConnectionManager.getInstance().getDataService().getModerators();
        return moderators;
    }

    public List<User> getUsers() {
        List users = new ArrayList<>();
        users = ConnectionManager.getInstance().getDataService().getUsers();
        return users;
    }

    public void addUser(User user){
        ConnectionManager.getInstance().getDataService().createUser(user);
}

    public User getSuperUser()  {
        return (User) ConnectionManager.getInstance().getDataService().getSuperUser();
    }

    public void removeModerator (User moderator){
        if (CinemaAssociations.getInstance().getListOf(moderator).size() == 0) {
            int answer = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить модератора с именем " + moderator.getUserName());
            if (answer == 0) {
                ConnectionManager.getInstance().getDataService().deleteModerator(moderator);
            }
        } else
            JOptionPane.showMessageDialog(null, "Данный модератор имеет нагрузку.\n" +
                    "Для начала необходимо перераспределить курируемые кинообъединения", "Ошибка", JOptionPane.PLAIN_MESSAGE);
    }
    public void addModerator(User moderator){
        try {
            ConnectionManager.getInstance().getDataService().createModerator(moderator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getCurrUser(){
        return curUser;
    }
    public void updateModerator(User moderator){
        ConnectionManager.getInstance().getDataService().updateModerator(moderator);
    }
}
