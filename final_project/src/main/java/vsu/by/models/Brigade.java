package vsu.by.models;

import java.util.List;

/**
 * класс бригады
 *
 * @author Kovzov Denis
 * @see vsu.by.models.Entity
 */

public class Brigade extends Entity {
    private String name;
    private List<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
