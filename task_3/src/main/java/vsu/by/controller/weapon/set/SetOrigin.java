package vsu.by.controller.weapon.set;

import vsu.by.models.Weapon;

public class SetOrigin implements FieldSetter {
    @Override
    public void set(Weapon weapon, String data) {
        weapon.setOrigin(data);
    }
}
