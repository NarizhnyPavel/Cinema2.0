package Kinopoisk.api.data.User;

public interface UserRoles {
    UserRole SUPER_USER = new UserRole(1, "SuperUser");
    UserRole MODERATOR = new UserRole(2, "Moderator");
    UserRole USER = new UserRole(3, "User");
}
