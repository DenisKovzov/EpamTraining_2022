package by.vsu;

import by.vsu.Actions.*;
import by.vsu.models.ammunitions.AmmunitionHighTier;
import by.vsu.models.ammunitions.AmmunitionLowTier;
import by.vsu.models.Action;
import by.vsu.models.Knight;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Knight knight;
    private Scanner scanner;
    private Map<Integer, ActionsMenu> mapChoose;

    private final String avaibleAction;


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
        initializeMap();
    }

    public void start() {
        int chooseAction;
        System.out.println(avaibleAction);

        while (true) {

            chooseAction = scanner.nextInt();

            if (chooseAction == Action.EXIT.ordinal()) {
                System.out.println("Good Luck");
                return;
            }

            mapChoose.get(chooseAction).call(knight);

            System.out.println("Введите что-нибудь еще для продолжения");
        }

    }

    private void setStartVariables() {
        knight.addAmmunition(new AmmunitionHighTier("Интерактивный шлем", 5));
        knight.addAmmunition(new AmmunitionHighTier("Композитная броня", 12));
        knight.addAmmunition(new AmmunitionLowTier("Бронежилет", 20));
        knight.addAmmunition(new AmmunitionLowTier("Заплечный резервуар", 7));
    }

    private void initializeMap() {
        mapChoose = new HashMap<>();

        mapChoose.put(Action.ADD_AMMUNITION.ordinal(), new ActionAddAmmunition());
        mapChoose.put(Action.SORT.ordinal(), new ActionSortAmmunition());
        mapChoose.put(Action.PRINT.ordinal(), new ActionShowAmmunition());
        mapChoose.put(Action.SELECT_BETWEEN_PRICE.ordinal(), new ActionSelectBetweenPriceAmmunition());
        mapChoose.put(Action.GET_PRICE.ordinal(), new ActionGetPriceAmmunition());
    }

}
