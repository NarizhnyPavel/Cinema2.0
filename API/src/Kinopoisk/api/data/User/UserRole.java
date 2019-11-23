package Kinopoisk.api.data.User;

import java.io.Serializable;

public class UserRole implements Serializable {
    private int id;
    private String name;

    public UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserRole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRole getUserRole(int index){
        switch (index){
            case 1: return UserRoles.SUPER_USER;
            case 2: return UserRoles.MODERATOR;
            case 3: return UserRoles.USER;
            default: return UserRoles.MODERATOR;
        }
    }
}
