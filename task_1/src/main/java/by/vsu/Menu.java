package by.vsu;

import by.vsu.models.ammunitions.Ammunition;
import by.vsu.models.ammunitions.AmmunitionHighTier;
import by.vsu.models.ammunitions.AmmunitionLowTier;
import by.vsu.comparators.WeightComparator;
import by.vsu.filters.Filter;
import by.vsu.filters.PriceFilter;
import by.vsu.models.Action;
import by.vsu.models.Knight;
import by.vsu.models.TypeAmmunition;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Knight knight;
    private Scanner scanner;

    private String avaibleAction;


    public Menu(Knight knight, Scanner scanner) {
        this.knight = knight;
        this.scanner = scanner;
        avaibleAction =
                "0 Выход\n" +
                        "1 Добавить аммуницию\n" +
                        "2 Отсортировать по весу\n" +
                        "3 Вывести на экран\n" +
                        "4 Выбрать по цене\n" +
                        "5 Получить цену аммуниции";
        setStartVariables();
    }

    public void start() {
        int chooseAction;
        System.out.println(avaibleAction);

        while (true) {

            chooseAction = scanner.nextInt();

            if (chooseAction == Action.EXIT.ordinal()) {
                System.out.println("Good Luck");
                return;
            } else if (chooseAction == Action.ADD_AMMUNITION.ordinal()) {
                addAmmunition();
            } else if (chooseAction == Action.SORT.ordinal()) {
                knight.sortByWeight(new WeightComparator());
            } else if (chooseAction == Action.PRINT.ordinal()) {
                System.out.println(knight);
            } else if (chooseAction == Action.SELECT_BETWEEN_PRICE.ordinal()) {
                selectBetweenPrice();
            } else if (chooseAction == Action.GET_PRICE.ordinal()) {
                System.out.printf("" + knight.getPriceAmmunition() + "\n");
            }
            System.out.println("Введите что-нибудь еще для продолжения");
        }

    }

    private void addAmmunition() {

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

    private void selectBetweenPrice() {
        float priceMin, priceMax;
        System.out.println("Введите минимальную и максимальную цену");
        priceMin = scanner.nextFloat();
        priceMax = scanner.nextFloat();
        Filter filter = new PriceFilter(priceMin, priceMax);

        List<Ammunition> ammunitions = knight.getElementsByFilter(filter);

        for (Ammunition i : ammunitions) {
            System.out.println(i);
        }

    }

    private void setStartVariables() {
        knight.addAmmunition(new AmmunitionHighTier("Интерактивный шлем", 5));
        knight.addAmmunition(new AmmunitionHighTier("Композитная броня", 12));
        knight.addAmmunition(new AmmunitionLowTier("Бронежилет", 20));
        knight.addAmmunition(new AmmunitionLowTier("Заплечный резервуар", 7));
    }
}
