package by.vsu.models.ammunitions;

import java.math.BigDecimal;

public class AmmunitionLowTier extends Ammunition {

    public AmmunitionLowTier(String name, double weight) {
        super(name, weight);
    }

    @Override
    public BigDecimal getPrice() {

        return new BigDecimal(getWeight());
    }
}
