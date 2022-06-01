package vsu.by.models;

import java.io.Serializable;

/**
 * абстрактный класс для объектов базы данных
 * содержащий id которое свойственно всем сущностям
 *
 * @author Kovzov Denis
 */

abstract public class Entity implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
