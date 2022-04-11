package vsu.by.models.visualWeapon;

import java.util.Objects;

public abstract class VisualTypeWeapon {

    private String name;
    private String value;

    public VisualTypeWeapon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualTypeWeapon that = (VisualTypeWeapon) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return
                "{name='" + name + '\'' +
                        ", value='" + value + '\'' +
                        '}';
    }
}
