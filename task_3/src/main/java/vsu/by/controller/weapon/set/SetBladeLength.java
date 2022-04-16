package vsu.by.controller.weapon.set;

import vsu.by.models.Weapon;
import vsu.by.models.visualWeapon.BladeLength;

public class SetBladeLength implements FieldSetter {
    @Override
    public void set(Weapon weapon, String data) {
        weapon.addVisual(new BladeLength(data));
    }
}
