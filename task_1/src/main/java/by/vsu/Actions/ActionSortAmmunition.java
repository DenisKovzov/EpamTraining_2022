package by.vsu.Actions;

import by.vsu.AmmunitionService;
import by.vsu.comparators.WeightComparator;
import by.vsu.models.Knight;

public class ActionSortAmmunition implements ActionsMenu {
    @Override
    public void call(Knight knight) {
        AmmunitionService.sortByWeight(knight.getAmmunitions(), new WeightComparator());
    }
}
