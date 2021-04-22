package plain;

public class Pyro {
    public void eat() {
        Pizza pizza = new Pizza();
        String message = String.format("파이로는 %s를 맛있게 먹어요", pizza);
        System.out.println(message);
    }
}
