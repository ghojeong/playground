package dip;

public class Restaurant {
    public static void run() {
        Human pyro = new Pyro();
        Food pizza = new Pizza();
        pyro.eat(pizza);
    }
}
