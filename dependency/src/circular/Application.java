package circular;

public class Application {
    public static void main(String[] args) {
        Human pyro = new Pyro();
        Human chef = new Chef();

        Food chefPizza = Pizza.madeBy(chef);
        Food pyroPizza = Pizza.madeBy(pyro);

        pyro.eat(chefPizza);
        pyro.eat(pyroPizza);
    }
}
