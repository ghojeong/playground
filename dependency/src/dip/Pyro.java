package dip;

public class Pyro implements Human {
    @Override
    public void eat(Food food) {
        Pizza pizza = new Pizza();
        String message = String.format("파이로는 %s를 맛있게 먹어요", food);
        System.out.println(message);
    }
}
