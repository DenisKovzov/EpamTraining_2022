package vsu.by.models;

/**
 * класс сотрудника
 *
 * @author Kovzov Denis
 * @see vsu.by.models.Entity
 * @see vsu.by.models.User
 */
public class Employee extends User {
    private Position position;
    private Long brigadeId = null;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Long getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(Long brigadeId) {
        this.brigadeId = brigadeId;
    }
}
