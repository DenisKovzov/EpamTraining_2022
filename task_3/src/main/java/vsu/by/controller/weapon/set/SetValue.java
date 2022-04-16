package vsu.by.controller.weapon.set;

import vsu.by.models.Weapon;

public class SetValue implements FieldSetter {
    @Override
    public void set(Weapon weapon, String data) {
        weapon.setValue(Boolean.valueOf(data));
    }
}
