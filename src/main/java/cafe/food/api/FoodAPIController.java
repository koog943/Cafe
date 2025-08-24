package cafe.food.api;

import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.request.FoodType;
import cafe.food.response.ResFood;
import cafe.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cafe.food.request.FoodType.DRINK;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodAPIController {

    private final FoodService foodService;

    @GetMapping("test")
    public String test() {
        return "ok";
    }

    @PostMapping("/post")
    public Food foodPost(@RequestBody ResFood resFood) {
        Food food = null;
        if (resFood.getFoodType() == FoodType.DESSERT) {
            food = Dessert.builder()
                    .name(resFood.getName())
                    .price(resFood.getPrice())
                    .quantity(resFood.getQuantity())
                    .build();
        } else if (resFood.getFoodType() == DRINK) {
            food = Drink.builder()
                    .name(resFood.getName())
                    .price(resFood.getPrice())
                    .quantity(resFood.getQuantity())
                    .build();
        } else {
            return food;
        }

        foodService.save(food);
        return food;
    }

    @GetMapping("/{id}")
    public Food foodId(@PathVariable(value = "id") Long id) {
        Food food = foodService.findById(id);
        return food;
    }

    @PatchMapping("/editName/{id}")
    public Food editFoodName(@PathVariable(value = "id") Long id, @RequestBody ResFood resFood) {
        Food food = foodService.editName(id, resFood.getName());
        return food;
    }

    @PatchMapping("/editPrice/{id}")
    public Food editFoodPrice(@PathVariable(value = "id") Long id, @RequestBody ResFood resFood) {
        Food food = foodService.editPrice(id, resFood.getPrice());
        return food;
    }

    @GetMapping("/foodList")
    public List<Food> foodList() {
        List<Food> foods = foodService.foodList();
        return foods;
    }
}
