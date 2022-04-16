package vsu.by.controller.weapon.set;

import vsu.by.models.Weapon;
import vsu.by.models.WeaponType;

public class SetWeaponType implements FieldSetter {
    @Override
    public void set(Weapon weapon, String data) {
        weapon.setWeaponType(WeaponType.valueOf(data));
    }
}
