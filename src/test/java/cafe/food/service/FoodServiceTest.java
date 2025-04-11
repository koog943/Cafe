package cafe.food.service;

import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.repository.FoodRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FoodServiceTest {

    @Autowired
    FoodService foodService;

    @Autowired
    FoodRepository foodRepository;

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

        Assertions.assertThat(findDessert).isEqualTo(dessert);
        Assertions.assertThat(findDrink).isEqualTo(drink);
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

        Dessert findDessert = (Dessert) foodRepository.findByName("케이크").orElse(null);
        Drink findDrink = (Drink) foodRepository.findByName("아메리카노").orElse(null);

        Assertions.assertThat(findDessert).isEqualTo(dessert);
        Assertions.assertThat(findDrink).isEqualTo(drink);
    }

}