package Kinopoisk.api.data.User;

import java.io.Serializable;

/**
 * Класс, описывающий роль в системе для {@link User}
 * @author Narizhny Pavel
 * @version 1.0
 */
public class UserRole implements Serializable {
    /** числовой идентификатор*/
    private int id;
    /** имя роли*/
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
