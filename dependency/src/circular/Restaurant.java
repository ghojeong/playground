package circular;

public class Restaurant {
    public static void run() {
        Human pyro = new Pyro();
        Chef chef = new Chef();

        Food chefPizza = chef.makePizza();

        pyro.eat(chefPizza);
    }
}
