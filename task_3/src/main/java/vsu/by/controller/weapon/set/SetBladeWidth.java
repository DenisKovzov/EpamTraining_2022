package vsu.by.controller.weapon.set;

import vsu.by.models.Weapon;
import vsu.by.models.visualWeapon.BladeWidth;

public class SetBladeWidth implements FieldSetter {
    @Override
    public void set(Weapon weapon, String data) {
        weapon.addVisual(new BladeWidth(data));
    }
}
