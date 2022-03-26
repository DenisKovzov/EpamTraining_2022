package by.vsu.Actions;

import by.vsu.models.Knight;
import by.vsu.models.TypeAmmunition;
import by.vsu.models.ammunitions.AmmunitionHighTier;
import by.vsu.models.ammunitions.AmmunitionLowTier;

import java.util.Scanner;

public class ActionAddAmmunition implements ActionsMenu {
    @Override
    public void call(Knight knight) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя аммуниции");
        String name;
        scanner.nextLine();
        name = scanner.nextLine();

        System.out.println("Введите вес");
        double weight;
        weight = scanner.nextDouble();

        System.out.println("Введите тип аммуниции\n 0 - высокое качество\n 1 - низкое");
        int chooseAmmunition;
        chooseAmmunition = scanner.nextInt();

        if (chooseAmmunition == TypeAmmunition.HIGH_TIER.ordinal()) {
            knight.addAmmunition(new AmmunitionHighTier(name, weight));
        } else if (chooseAmmunition == TypeAmmunition.LOW_TIER.ordinal()) {
            knight.addAmmunition(new AmmunitionLowTier(name, weight));
        } else {

        }
    }
}
