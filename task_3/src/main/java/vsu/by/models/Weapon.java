package vsu.by.models;

import vsu.by.models.visualWeapon.VisualTypeWeapon;

import java.util.HashSet;
import java.util.Set;

public class Weapon {
    private String identity;
    private WeaponType weaponType;
    private HandleType handleType;
    private String origin;
    private Set<VisualTypeWeapon> visual;
    private boolean value;

    public Weapon() {
        visual = new HashSet<>();
    }

    public void addVisual(VisualTypeWeapon visual) {
        this.visual.add(visual);
    }

    public String getId() {
        return identity;
    }

    public void setId(String id) {
        this.identity = id;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public HandleType getHandleType() {
        return handleType;
    }

    public void setHandleType(HandleType handleType) {
        this.handleType = handleType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Set<VisualTypeWeapon> getVisual() {
        return visual;
    }

    public void setVisual(Set<VisualTypeWeapon> visual) {
        this.visual = visual;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "identity='" + identity + '\'' +
                ", weaponType=" + weaponType +
                ", handleType=" + handleType +
                ", origin='" + origin + '\'' +
                ", visual=" + visual +
                ", value=" + value +
                '}';
    }
}
