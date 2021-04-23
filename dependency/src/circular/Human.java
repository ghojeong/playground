package circular;

public abstract class Human {
    public void eat(Food food) {
        String message = String.format("%s는 %s를 맛있게 먹어요", this, food);
        System.out.println(message);
    }

    @Override
    public abstract String toString();
}
