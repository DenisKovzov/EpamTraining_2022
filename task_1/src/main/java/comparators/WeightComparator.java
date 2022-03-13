package comparators;

import models.ammunitions.Ammunition;
import java.util.Comparator;

public class WeightComparator implements Comparator<Ammunition> {
    @Override
    public int compare(Ammunition o1, Ammunition o2)
    {
        return Double.compare(o1.getWeight(),o2.getWeight());
    }
}
