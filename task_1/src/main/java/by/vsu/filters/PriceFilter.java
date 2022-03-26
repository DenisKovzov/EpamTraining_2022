package by.vsu.filters;

import by.vsu.models.ammunitions.Ammunition;

public class PriceFilter implements Filter {

    private float priceMin;
    private float priceMax;

    public PriceFilter(float priceMin, float priceMax) {
        setPrice(priceMin, priceMax);
    }

    private void setPrice(float priceMin, float priceMax) {
        if (priceMax < 0 || priceMin < 0 || priceMax < priceMin) {
            throw new IllegalArgumentException("wrong price");
        }
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    @Override
    public boolean check(Ammunition ammunition) {
        return (ammunition.getPrice() >= priceMin && ammunition.getPrice() <= priceMax);
    }
}
