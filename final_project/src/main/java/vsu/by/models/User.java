package vsu.by.models;

/**
 * класс пользователя
 *
 * @author Kovzov Denis
 * @see vsu.by.models.Entity
 */
public class User extends Entity {
    private String login;
    private String password;
    private Role role;

    @Override
    public String toString() {
        return "Login: " + login + " password: " + password +
                " role: " + role.toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
