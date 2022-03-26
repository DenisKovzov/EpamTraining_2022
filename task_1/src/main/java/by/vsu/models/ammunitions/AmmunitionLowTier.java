package by.vsu.models.ammunitions;

public class AmmunitionLowTier extends Ammunition {

    public AmmunitionLowTier(String name, double weight) {
        super(name,weight);
    }

    @Override
    public double getPrice() {

        return getWeight();
    }
}
