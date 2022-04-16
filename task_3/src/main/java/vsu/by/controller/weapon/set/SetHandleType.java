package vsu.by.controller.weapon.set;

import vsu.by.models.HandleType;
import vsu.by.models.Weapon;

public class SetHandleType implements FieldSetter {
    @Override
    public void set(Weapon weapon, String data) {
        weapon.setHandleType(HandleType.valueOf(data));
    }
}
