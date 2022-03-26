package by.vsu.models.ammunitions;

import java.math.BigDecimal;

public class AmmunitionHighTier extends Ammunition {

    public AmmunitionHighTier(String name, double weight) {
        super(name, weight);
    }


    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(2 * getWeight());
    }
}
