package models.ammunitions;


public abstract class Ammunition {
    private String name;
    private double weight;

    public Ammunition(String name, double weight) {
        this.weight = weight;
        this.name  = name;
    }

    public double getWeight() {
        return weight;
    }

    abstract public double getPrice();

    @Override
    public String toString() {
        return name + ", weight: " + weight;
    }
}
