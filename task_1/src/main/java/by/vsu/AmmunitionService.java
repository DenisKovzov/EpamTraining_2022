package by.vsu;

import by.vsu.filters.Filter;
import by.vsu.models.ammunitions.Ammunition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AmmunitionService {
    static public double getPriceAmmunition(List<Ammunition> ammunitions) {

        double price = 0;

        for (Ammunition i : ammunitions) {
            price += i.getPrice().doubleValue();
        }

        return price;
    }

    static public List<Ammunition> getElementsByFilter(List<Ammunition> ammunitions, Filter filter) {

        List<Ammunition> temp = new ArrayList<>();

        for (Ammunition i : ammunitions) {

            if (filter.check(i)) {
                temp.add(i);
            }
        }

        return temp;
    }

    static public void sortByWeight(List<Ammunition> ammunitions, Comparator<Ammunition> comparator) {
        ammunitions.sort(comparator);
    }
}
