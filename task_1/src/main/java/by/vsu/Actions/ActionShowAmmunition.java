package by.vsu.Actions;

import by.vsu.models.Knight;

public class ActionShowAmmunition implements ActionsMenu {
    @Override
    public void call(Knight knight) {
        System.out.println(knight);
    }
}
