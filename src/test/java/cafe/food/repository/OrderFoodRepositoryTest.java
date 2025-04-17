//package cafe.food.repository;
//
//import cafe.food.domain.food.*;
//import jakarta.persistence.EntityManager;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class OrderFoodRepositoryTest {
//
//    @Autowired
//    OrderFoodRepository orderFoodRepository;
//
//    @Autowired
//    FoodRepository foodRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    @Transactional
//    void orderFood() {
//        Dessert dessert = saveDessert();
//        Drink drink = saveDrink();
//        Dessert findDessert = (Dessert) foodRepository.findById(dessert.getId()).orElse(null);
//        Drink findDrink = (Drink) foodRepository.findById(drink.getId()).orElse(null);
//
//        OrderFood orderDessert = OrderFood.builder()
//                .food(findDessert)
//                .count(3)
//                .build();
//
//        OrderFood orderDrink = OrderFood.builder()
//                .food(findDrink)
//                .count(2)
//                .build();
//
//        orderFoodRepository.save(orderDessert);
//        orderFoodRepository.save(orderDrink);
//
//        OrderFood findOrderDrink = orderFoodRepository.findById(orderDrink.getId()).orElse(null);
//        OrderFood findOrderDessert = orderFoodRepository.findById(orderDessert.getId()).orElse(null);
//
//        Assertions.assertThat(findOrderDrink).isNotNull();
//        Assertions.assertThat(findOrderDrink.getCount()).isEqualTo(2);
//
//        Assertions.assertThat(findOrderDessert).isNotNull();
//        Assertions.assertThat(findOrderDessert.getCount()).isEqualTo(3);
//    }
//
//    @Transactional
//    private Drink saveDrink() {
//        Drink drink = Drink.builder()
//                .name("아메리카노")
//                .price(3000)
//                .quantity(5)
//                .build();
//
//        foodRepository.save(drink);
//        return drink;
//    }
//
//    @Transactional
//    private Dessert saveDessert() {
//        Dessert dessert = Dessert.builder()
//                .name("케이크")
//                .price(5000)
//                .quantity(2)
//                .build();
//
//        foodRepository.save(dessert);
//        return  dessert;
//    }
//}