package by.vsu.models.ammunitions;

public class AmmunitionHighTier extends Ammunition {

    public AmmunitionHighTier(String name, double weight) {
        super(name, weight);
    }


    @Override
    public double getPrice() {
        return 2 * getWeight();
    }
}
