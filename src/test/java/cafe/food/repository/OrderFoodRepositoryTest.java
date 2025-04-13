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
        Dessert dessert = saveDessert();
        Drink drink = saveDrink();
        Dessert findDessert = (Dessert) foodRepository.findById(dessert.getId()).orElse(null);
        Drink findDrink = (Drink) foodRepository.findById(drink.getId()).orElse(null);

        OrderFood orderDessert = OrderFood.builder()
                .food(findDessert)
                .build();

        OrderFood orderDrink = OrderFood.builder()
                .food(findDrink)
                .build();

        OrderFood order1 = orderFoodRepository.save(orderDessert);
        OrderFood order2 = orderFoodRepository.save(orderDrink);

        OrderFood findFood1 = orderFoodRepository.findById(order1.getId()).orElse(null);
        OrderFood findFood2 = orderFoodRepository.findById(order2.getId()).orElse(null);

        Assertions.assertThat(findFood1).isEqualTo(orderDessert);
        Assertions.assertThat(findFood2).isEqualTo(orderDrink);

    }

    private Drink saveDrink() {
        Drink drink = Drink.builder()
                .name("아메리카노")
                .price(3000)
                .quantity(5)
                .build();

        foodRepository.save(drink);

        return drink;
    }

    private Dessert saveDessert() {
        Dessert dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(2)
                .build();

        foodRepository.save(dessert);
        return  dessert;
    }
}