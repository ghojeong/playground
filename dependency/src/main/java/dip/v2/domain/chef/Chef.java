package dip.v2.domain.chef;

import dip.v2.domain.food.Food;

public interface Chef {
    String getName();

    Food createPizza();

    Food createPasta();

    Food createSteak();
}
