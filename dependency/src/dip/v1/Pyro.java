package dip.v1;

public class Pyro implements Client {
    @Override
    public void eat(Food food) {
        String message = String.format("파이로는 %s를 맛있게 먹어요", food);
        System.out.println(message);
    }
}
