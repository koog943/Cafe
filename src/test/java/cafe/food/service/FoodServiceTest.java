package cafe.food.service;

import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.repository.FoodRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FoodServiceTest {

    @Autowired
    FoodService foodService;

    @Autowired
    FoodRepository foodRepository;

    @BeforeEach
    void clear() {
        foodRepository.deleteAll();
    }

    @Test
    void save() {
        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(100)
                .build();

        Food drink = Drink.builder()
                .name("아메리카노")
                .price(3000)
                .quantity(200)
                .build();

        Long saveDessertId = foodService.save(dessert);
        Long saveDrinkId = foodService.save(drink);

        Dessert findDessert = (Dessert) foodRepository.findById(saveDessertId).orElse(null);
        Drink findDrink = (Drink) foodRepository.findById(saveDrinkId).orElse(null);

        assertThat(findDessert).isNotNull();
        assertThat(findDrink).isNotNull();
    }

    @Test
    void findByName() {
        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(100)
                .build();

        Food drink = Drink.builder()
                .name("아메리카노")
                .price(3000)
                .quantity(200)
                .build();

        foodService.save(dessert);
        foodService.save(drink);

        Dessert findDessert = (Dessert) foodService.findByName("케이크");
        Drink findDrink = (Drink) foodService.findByName("아메리카노");

        assertThat(findDessert).isNotNull();
        assertThat(findDrink).isNotNull();
    }

    @Test
    void findByNameWithOrderFood() {
        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(100)
                .build();

        foodService.save(dessert);

        Dessert findDessert = (Dessert) foodService.findByNameWithOrderFood("케이크");

        assertThat(findDessert.getOrderFoods()).isNotNull();
    }

    @Test
    void foodList() {
        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(100)
                .build();

        Food drink = Drink.builder()
                .name("아메리카노")
                .price(3000)
                .quantity(200)
                .build();

        foodService.save(dessert);
        foodService.save(drink);

        List<Food> foods = foodService.foodList();
        assertThat(foods.size()).isEqualTo(2);
    }

}