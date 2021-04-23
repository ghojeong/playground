package dip;

public class Application {
    public static void main(String[] args) {
        Human pyro = new Pyro();
        Food pizza = new Pizza();
        pyro.eat(pizza);
    }
}
