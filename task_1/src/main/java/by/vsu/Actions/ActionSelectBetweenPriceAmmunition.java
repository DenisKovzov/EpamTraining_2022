package by.vsu.Actions;

import by.vsu.AmmunitionService;
import by.vsu.filters.Filter;
import by.vsu.filters.PriceFilter;
import by.vsu.models.Knight;
import by.vsu.models.ammunitions.Ammunition;

import java.util.List;
import java.util.Scanner;

public class ActionSelectBetweenPriceAmmunition implements ActionsMenu {
    @Override
    public void call(Knight knight) {

        Scanner scanner = new Scanner(System.in);

        float priceMin, priceMax;

        System.out.println("Введите минимальную и максимальную цену");

        priceMin = scanner.nextFloat();
        priceMax = scanner.nextFloat();

        Filter filter = new PriceFilter(priceMin, priceMax);

        List<Ammunition> ammunitions = AmmunitionService.getElementsByFilter(knight.getAmmunitions(), filter);

        for (Ammunition i : ammunitions) {
            System.out.println(i);
        }
    }
}
