package vsu.by.models;

/**
 * enum должностей для сотрудника
 *
 * @author Kovzov Denis
 */
public enum Position {
    PILOT("Пилот"),
    NAVIGATOR("Штурман"),
    RADIO_OPERATOR("Радист"),
    STEWARDESS("Стюардесса");

    private String name;

    private Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
