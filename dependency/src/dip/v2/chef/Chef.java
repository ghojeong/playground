package dip.v2.chef;

import dip.v2.food.Food;

public interface Chef {
    Food createPizza();

    Food createPasta();

    Food createSteak();
}
