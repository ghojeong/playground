package circular;

public class RestaurantApplication {
    public static void main(String[] args) {
        Chef chef = new Gordon();
        Food chefPizza = chef.makePizza();

        Client pyro = new Pyro();
        pyro.eat(chefPizza);
    }
}
