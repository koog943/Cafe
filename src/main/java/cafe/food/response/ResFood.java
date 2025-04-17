package cafe.food.response;

import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.domain.member.Address;
import cafe.food.domain.member.Member;
import cafe.food.request.FoodType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResFood {

    private String name;

    private int price;

    private int quantity;

    @JsonProperty("foodType")
    private FoodType foodType;

    public ResFood(Food food) {
        this.name = food.getName();
        this.price = food.getPrice();
        this.quantity = food.getQuantity();
        if (food instanceof Drink) {
            this.foodType = FoodType.DRINK;
        } else if (food instanceof Dessert) {
            this.foodType = FoodType.DESSERT;
        }
    }

    public ResFood() {

    }

}
