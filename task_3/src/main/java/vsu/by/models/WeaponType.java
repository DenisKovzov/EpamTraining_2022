package vsu.by.models;

public enum WeaponType {
    KNIFE("knife"), DAGGER("dagger"), SABER("saber");

    private String name;

    WeaponType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}