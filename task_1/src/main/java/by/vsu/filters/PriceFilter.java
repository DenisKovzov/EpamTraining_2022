package by.vsu.filters;

import by.vsu.models.ammunitions.Ammunition;

public class PriceFilter implements Filter {

    private float priceMin;
    private float priceMax;

    public PriceFilter(float priceMin, float priceMax) {
        validate(priceMin, priceMax);
        setRange(priceMin, priceMax);
    }

    private void setRange(float priceMin, float priceMax) {

        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    private void validate(float priceMin, float priceMax) {
        if (priceMax < 0 || priceMin < 0 || priceMax < priceMin) {
            throw new IllegalArgumentException("wrong price");
        }
    }

    @Override
    public boolean check(Ammunition ammunition) {
        return (ammunition.getPrice().floatValue() >= priceMin && ammunition.getPrice().floatValue() <= priceMax);
    }
}
