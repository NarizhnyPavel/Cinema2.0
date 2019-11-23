package Kinopoisk.api.data.User;

import Kinopoisk.api.services.DataService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class User implements Serializable {
    protected long id;
    protected String userName;
    protected UserRole role;
    protected Long lastSession;
    protected String password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.updateSession();
    }

    public User(String name) {
        this.userName = name;
        this.setRandomPassword();
        this.updateSession();
    }

    public String getUserName() {
        return userName;
    }

    public UserRole getRole() {
        return role;
    }

    public User(long id, String userName, UserRole role, String password, long lastSession) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.password = password;
        this.lastSession = lastSession;
        /*if (lastSession == 0)
            this.updateSession();*/
    }

    public long getId() {
        return id;
    }

    public void setRole (UserRole role){
        this.role = role;
    }

    public void updateSession(){
        this.lastSession = new Date().getTime();
        }

    public String getLastSession(){
        SimpleDateFormat date = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        String curDate = date.format(new Date(lastSession));
        return curDate;
    }

    public Long getLastSessionLong(){
        return this.lastSession;
    }
    public void setUserName(String name) {
        this.userName = name;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRandomPassword(){
        int from = 8;
        int to = 12;
        String pass  = "";
        Random r     = new Random();
        int cntchars = from + r.nextInt(to - from + 1);

        for (int i = 0; i < cntchars; ++i) {
            char next = 0;
            int range = 10;

            switch(r.nextInt(3)) {
                case 0: {next = '0'; range = 10;} break;
                case 1: {next = 'a'; range = 26;} break;
                case 2: {next = 'A'; range = 26;} break;
            }

            pass += (char)((r.nextInt(range)) + next);
        }

        this.password = pass;
    }

    public String getInfo() {
        String info = "ID: " + id + "\n";
        info += "Password: " + password + "\n";
        info += "Запишите эти данные, чтобы не потерять";
        return info;
    }

    public String getPassword() {
        return password;
    }


}
