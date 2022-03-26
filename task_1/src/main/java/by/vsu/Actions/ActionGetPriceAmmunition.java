package by.vsu.Actions;

import by.vsu.AmmunitionService;
import by.vsu.models.Knight;

public class ActionGetPriceAmmunition implements ActionsMenu {
    @Override
    public void call(Knight knight) {
        System.out.println(AmmunitionService.getPriceAmmunition(knight.getAmmunitions()) + "\n");
    }
}
