package dip.v1;

public class RestaurantApplication {
    public static void main(String[] args) {
        Client pyro = new Pyro();
        Food pizza = new Pizza();
        pyro.eat(pizza);
    }
}
