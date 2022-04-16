package vsu.by.controller.weapon.set;

import vsu.by.models.Weapon;
import vsu.by.models.visualWeapon.Bloodstream;

public class SetBloodStream implements FieldSetter {
    @Override
    public void set(Weapon weapon, String data) {
        weapon.addVisual(new Bloodstream(data));
    }
}
