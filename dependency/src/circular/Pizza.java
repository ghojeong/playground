package circular;

public class Pizza implements Food {
    private final Human chef;

    private Pizza(Human chef) {
        this.chef = chef;
    }

    public static Pizza madeBy(Human chef) {
        Pizza pizza = new Pizza(chef);
        return pizza;
    }

    @Override
    public String toString() {
        return String.format("%s가 만든 피자", chef);
    }
}
