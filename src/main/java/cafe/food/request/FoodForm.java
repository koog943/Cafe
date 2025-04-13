package cafe.food.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FoodForm {
    private String name;

    private int price;

    private int quantity;

    private FoodType foodType;
}
