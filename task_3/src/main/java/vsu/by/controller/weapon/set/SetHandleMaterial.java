package vsu.by.controller.weapon.set;

import vsu.by.models.Weapon;
import vsu.by.models.visualWeapon.HandleMaterial;

public class SetHandleMaterial implements FieldSetter {
    @Override
    public void set(Weapon weapon, String data) {
        weapon.addVisual(new HandleMaterial(data));
    }
}
