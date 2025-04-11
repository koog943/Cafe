package cafe.food.repository;

import cafe.food.domain.food.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderFoodRepositoryTest {

    @Autowired
    OrderFoodRepository orderFoodRepository;

    @Autowired
    FoodRepository foodRepository;

    @Test
    void orderFood() {
        saveFood();
        Dessert findDessert = (Dessert) foodRepository.findById(1L).orElse(null);
        Drink findDrink = (Drink) foodRepository.findById(2L).orElse(null);

        OrderFood orderDessert = OrderFood.builder()
                .food(findDessert)
                .build();

        OrderFood orderDrink = OrderFood.builder()
                .food(findDrink)
                .build();

        orderFoodRepository.save(orderDessert);
        orderFoodRepository.save(orderDrink);

        OrderFood findFood1 = orderFoodRepository.findById(1L).orElse(null);
        OrderFood findFood2 = orderFoodRepository.findById(2L).orElse(null);

        Assertions.assertThat(findFood1).isEqualTo(orderDessert);
        Assertions.assertThat(findFood2).isEqualTo(orderDrink);

    }

    private void saveFood() {
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

        foodRepository.save(dessert);
        foodRepository.save(dirnk);
    }
}