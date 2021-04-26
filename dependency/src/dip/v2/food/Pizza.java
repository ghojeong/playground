package dip.v2.food;

public class Pizza implements Food {
    @Override
    public String serialize() {
        return "피자";
    }
}
