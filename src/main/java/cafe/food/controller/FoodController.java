package cafe.food.controller;

import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.request.FoodForm;
import cafe.food.request.FoodType;
import cafe.food.response.ResFood;
import cafe.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/foods/new")
    public String foodForm(Model model) {
        model.addAttribute("form", new FoodForm());
        model.addAttribute("foodTypes", FoodType.values());
        return "foods/createFoodForm";
    }

    @PostMapping("/foods/new")
    public String postFood(@ModelAttribute(name = "form") FoodForm foodForm, RedirectAttributes redirectAttributes) {
        if (foodForm.getFoodType() == FoodType.DESSERT) {
            Food dessert = Dessert.builder()
                    .name(foodForm.getName())
                    .price(foodForm.getPrice())
                    .quantity(foodForm.getQuantity())
                    .build();
            Long saveId = foodService.save(dessert);
            redirectAttributes.addAttribute("saveId", saveId);
        } else if(foodForm.getFoodType() == FoodType.DRINK) {
            Food drink = Drink.builder()
                    .name(foodForm.getName())
                    .price(foodForm.getPrice())
                    .quantity(foodForm.getQuantity())
                    .build();
            Long saveId = foodService.save(drink);
            redirectAttributes.addAttribute("saveId", saveId);
        }

        return "redirect:/foods/{saveId}";
    }

    @GetMapping("/foods/{id}")
    public String food(@PathVariable(name = "id") Long id, Model model) {
        Food food = foodService.findById(id);
        model.addAttribute("resFood", new ResFood(food));
        return "/foods/food";
    }

    @GetMapping("/foods")
    public String foodList(Model model) {
        List<ResFood> foodList = foodService.foodList().stream()
                .map((food) -> new ResFood(food))
                .collect(Collectors.toList());
        model.addAttribute("foodList", foodList);
        return "/foods/foodList";
    }


}
