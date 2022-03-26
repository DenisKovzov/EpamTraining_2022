package by.vsu.models.ammunitions;

import java.math.BigDecimal;

public class AmmunitionHighTier extends Ammunition {

    private final int HIGH_TIER_FACTOR = 2;

    public AmmunitionHighTier(String name, double weight) {
        super(name, weight);
    }


    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(HIGH_TIER_FACTOR * getWeight());
    }
}
