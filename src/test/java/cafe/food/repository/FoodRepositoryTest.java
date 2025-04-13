package cafe.food.repository;

import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FoodRepositoryTest {
    
    @Autowired
    FoodRepository foodRepository;

    @Test
    void save() {
        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(100)
                .build();

        Food dirnk = Drink.builder()
                .name("아메리카노")
                .price(3000)
                .quantity(200)
                .build();

        Long dessertId = foodRepository.save(dessert).getId();
        Long drinkId = foodRepository.save(dirnk).getId();

        Dessert findDessert = (Dessert) foodRepository.findById(dessertId).orElse(null);
        Drink findDrink = (Drink) foodRepository.findById(drinkId).orElse(null);

        Assertions.assertThat(findDessert).isEqualTo(dessert);
        Assertions.assertThat(findDrink).isEqualTo(dirnk);

    }
}