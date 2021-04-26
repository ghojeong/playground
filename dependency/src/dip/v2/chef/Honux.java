package dip.v2.chef;

import dip.v2.food.Pasta;
import dip.v2.food.Pizza;
import dip.v2.food.Steak;

public class Honux implements Chef {
    @Override
    public Pizza createPizza() {
        System.out.println("호눅스가 피자를 만듭니다.");
        return new Pizza();
    }

    @Override
    public Pasta createPasta() {
        System.out.println("호눅스가 파스타를 만듭니다.");
        return new Pasta();
    }

    @Override
    public Steak createSteak() {
        System.out.println("호눅스가 스테이크를 만듭니다.");
        return new Steak();
    }
}
