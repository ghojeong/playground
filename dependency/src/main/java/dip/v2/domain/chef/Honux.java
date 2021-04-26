package dip.v2.domain.chef;

import dip.v2.domain.food.Food;
import dip.v2.domain.food.Pasta;
import dip.v2.domain.food.Pizza;
import dip.v2.domain.food.Steak;

public class Honux implements Chef {
    @Override
    public String getName() {
        return "호눅스";
    }

    @Override
    public Food createPizza() {
        System.out.println("호눅스가 피자를 만듭니다.");
        return new Pizza();
    }

    @Override
    public Food createPasta() {
        System.out.println("호눅스가 파스타를 만듭니다.");
        return new Pasta();
    }

    @Override
    public Food createSteak() {
        System.out.println("호눅스가 스테이크를 만듭니다.");
        return new Steak();
    }
}
