package circular;

public class Gordon implements Chef {
    @Override
    public Food makePizza() {
        Food pizza = Pizza.madeBy(this);
        System.out.println("고든 램지가 피자를 만듭니다.");
        return pizza;
    }

    @Override
    public String toString() {
        return "고든 램지";
    }
}
