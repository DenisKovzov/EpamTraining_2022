package vsu.by.models;

/**
 * enum статусов для рейса
 *
 * @author Kovzov Denis
 */
public enum Status {
    ACTIVE("Активен"),
    NO_ACTIVE("Не активен"),
    CHANGED("Изменен"),
    CANCELED("Отменен");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
