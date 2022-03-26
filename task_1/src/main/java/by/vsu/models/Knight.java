package by.vsu.models;

import by.vsu.models.ammunitions.Ammunition;
import by.vsu.filters.Filter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Knight {

    private List<Ammunition> data;

    public Knight() {
        data = new ArrayList<>();
    }

    public void addAmmunition(Ammunition element) {
        data.add(element);
    }


    public double getPriceAmmunition() {

        double price = 0;

        for (Ammunition i : data) {
            price += i.getPrice();
        }

        return price;
    }

    public List<Ammunition> getElementsByFilter(Filter filter) {

        List<Ammunition> temp = new ArrayList<>();

        for (Ammunition i : data) {

            if (filter.check(i)) {
                temp.add(i);
            }
        }

        return temp;
    }

    public void sortByWeight(Comparator<Ammunition> comparator) {
        data.sort(comparator);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Ammunition i: data ) {
            stringBuilder.append(i.toString() + "\n");
        }

        return stringBuilder.toString();
    }
}
