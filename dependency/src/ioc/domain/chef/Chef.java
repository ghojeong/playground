package ioc.domain.chef;


import ioc.domain.food.Food;

public interface Chef {
    String getName();

    Food createPizza();

    Food createPasta();

    Food createSteak();
}
