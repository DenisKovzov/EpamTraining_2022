package vsu.by.comparators;

import vsu.by.models.Weapon;
import vsu.by.models.visualWeapon.BladeLength;

import java.util.Comparator;

public class ComparatorByBladeLength implements Comparator<Weapon> {
    @Override
    public int compare(Weapon weaponFirst, Weapon weaponSecond) {
        return Integer.compare(getValueBladeLength(weaponFirst), getValueBladeLength(weaponSecond));
    }

    private int getValueBladeLength(Weapon weapon) {
        return Integer.parseInt(weapon.getVisual().stream().
                filter(e -> e.getClass() == BladeLength.class).findFirst().get().getValue());
    }
}
