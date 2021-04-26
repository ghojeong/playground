package circular;

public class Pizza implements Food {
    private final Chef chef;

    private Pizza(Chef chef) {
        this.chef = chef;
    }

    public static Pizza madeBy(Chef chef) {
        return new Pizza(chef);

        // 무한 루프, 신입 때 실수해서, 엄청 많은 피자를 만들어서 DB 서버 터트림
        // return chef.makePizza();
    }

    @Override
    public String toString() {
        return String.format("%s가 만든 피자", chef);
    }
}
