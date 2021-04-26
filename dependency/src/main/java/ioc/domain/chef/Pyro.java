package ioc.domain.chef;

import ioc.domain.food.Food;
import ioc.domain.food.Pasta;
import ioc.domain.food.Pizza;
import ioc.domain.food.Steak;
import ioc.framework.annotation.Service;

@Service
public class Pyro implements Chef {
    @Override
    public String getName() {
        return "파이로";
    }

    @Override
    public Food createPizza() {
        System.out.println("파이로가 피자를 만듭니다.");
        return new Pizza();
    }

    @Override
    public Food createPasta() {
        System.out.println("파이로가 파스타를 만듭니다.");
        return new Pasta();
    }

    @Override
    public Food createSteak() {
        System.out.println("파이로가 스테이크를 만듭니다.");
        return new Steak();
    }
}
