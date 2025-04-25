package cafe.food.controller;

import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.request.FoodForm;
import cafe.food.request.FoodType;
import cafe.food.response.ResFood;
import cafe.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/foods/new")
    public String foodForm(Model model) {
        model.addAttribute("form", new FoodForm());
        model.addAttribute("foodTypes", FoodType.values());
        return "foods/createFoodForm";
    }

    @PostMapping("/foods/new")
    public String postFood(@ModelAttribute(name = "form") FoodForm foodForm,
                           BindingResult bindingResult,
                           @RequestParam(name = "file") MultipartFile file,
                           RedirectAttributes redirectAttributes) throws IOException {

        if(bindingResult.hasErrors()) {
            return "foods/createFoodForm";
        }
        if (foodForm.getFoodType() == FoodType.DESSERT) {
            Food dessert = Dessert.builder()
                    .name(foodForm.getName())
                    .price(foodForm.getPrice())
                    .quantity(foodForm.getQuantity())
                    .build();
            Long saveId = foodService.save(dessert);

            if (!file.isEmpty()) {
                String imageName = "food_" + saveId + ".png";
                String fullPath = fileDir + imageName;
                log.info("fullpath={}", fullPath);
                file.transferTo(new File(fullPath));
            }


            redirectAttributes.addAttribute("saveId", saveId);
        } else if(foodForm.getFoodType() == FoodType.DRINK) {
            Food drink = Drink.builder()
                    .name(foodForm.getName())
                    .price(foodForm.getPrice())
                    .quantity(foodForm.getQuantity())
                    .build();
            Long saveId = foodService.save(drink);

            if (!file.isEmpty()) {
                String imageName = "food_" + saveId + ".png";
                String fullPath = fileDir + imageName;
                log.info("fullpath={}", fullPath);
                file.transferTo(new File(fullPath));
            }


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
