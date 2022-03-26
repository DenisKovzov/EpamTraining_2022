package by.vsu.models;

import by.vsu.models.ammunitions.Ammunition;
import by.vsu.filters.Filter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Knight {

    private List<Ammunition> ammunitions;

    public Knight() {
        ammunitions = new ArrayList<>();
    }

    public void addAmmunition(Ammunition element) {
        ammunitions.add(element);
    }

    public List<Ammunition> getAmmunitions() {
        return ammunitions;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Ammunition i : ammunitions) {
            stringBuilder.append(i.toString() + "\n");
        }

        return stringBuilder.toString();
    }
}
