package circular;

public class Restaurant {
    public static void run() {
        Human pyro = new Pyro();
        Human chef = new Chef();

        Food chefPizza = Pizza.madeBy(chef);
        Food pyroPizza = Pizza.madeBy(pyro);

        pyro.eat(chefPizza);
        pyro.eat(pyroPizza);
    }
}
