package dip.v2.chef;

import dip.v2.food.Food;
import dip.v2.food.Pasta;
import dip.v2.food.Pizza;
import dip.v2.food.Steak;

public abstract class Chef {
    public abstract String getName();

    public Food createPizza() {
        Food food = new Pizza();
        printProcess(food);
        return food;
    }

    public Food createPasta() {
        Food food = new Pasta();
        printProcess(food);
        return food;
    }

    public Food createSteak() {
        Food food = new Steak();
        printProcess(food);
        return food;
    }

    private void printProcess(Food food) {
        System.out.println(String.format("%s가 %s를 만듭니다.", getName(), food.getName()));
    }
}
