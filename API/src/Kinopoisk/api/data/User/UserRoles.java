package Kinopoisk.api.data.User;

/**
 * Интерфейс, опиывающий список ролей пользователей
 * @see {@link User}
 * @author Narizhny Pavel
 * @version 1.0
 */
public interface UserRoles {
    UserRole SUPER_USER = new UserRole(1, "SuperUser");
    UserRole MODERATOR = new UserRole(2, "Moderator");
    UserRole USER = new UserRole(3, "User");
    UserRole IMAGE = new UserRole(4, "Image");
}
