package circular;

public class Chef extends Human {

    public Pizza makePizza() {
        return Pizza.madeBy(this);
    }

    @Override
    public String toString() {
        return "고든 램지";
    }
}
