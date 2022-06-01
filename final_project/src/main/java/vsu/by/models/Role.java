package vsu.by.models;

/**
 * enum ролей для пользователя
 *
 * @author Kovzov Denis
 */
public enum Role {
    ADMIN("администратор"),
    USER("пользователь"),
    DISPATCHER("диспетчер");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
