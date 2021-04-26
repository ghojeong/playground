package ioc;

import ioc.domain.chef.Chef;
import ioc.domain.food.Food;

public class ResponseDto {
    private final String chefName;
    private final String foodName;

    private ResponseDto(String chefName, String foodName) {
        this.chefName = chefName;
        this.foodName = foodName;
    }

    public static ResponseDto from(Chef chef, Food food) {
        return new ResponseDto(chef.getName(), food.getName());
    }

    public String serialize() {
        return String.format("%s가 만든 %s", chefName, foodName);
    }
}
