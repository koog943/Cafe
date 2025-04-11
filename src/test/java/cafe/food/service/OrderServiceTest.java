package cafe.food.service;

import cafe.food.domain.Order;
import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.domain.member.Address;
import cafe.food.domain.member.GRADE;
import cafe.food.domain.member.User;
import cafe.food.domain.status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    FoodService foodService;

    @Test
    void order() {
        User user = getUser();
        List<Food> foods = getFoods();

        Order order = orderService.order(user, foods);

        assertThat(order.getUser()).isEqualTo(user);
        assertThat(order.getOrderFoods().size()).isEqualTo(2);
        assertThat(order.getOrderPrice()).isEqualTo(25000);
    }

    @Test
    void cancel() {
        User user = getUser();
        List<Food> foods = getFoods();

        Order order = orderService.order(user, foods);
        assertThat(order.getOrderStatus()).isEqualTo(status.ORDER);

        Order cancelOrder = orderService.cancelOrder(order);
        assertThat(cancelOrder.getOrderStatus()).isEqualTo(status.CANCEL);
    }

    private List<Food> getFoods() {
        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(2)
                .build();

        Food drink = Drink.builder()
                .name("아메리카노")
                .price(3000)
                .quantity(5)
                .build();

        List<Food> foods = new ArrayList<>();
        foods.add(dessert);
        foods.add(drink);

        return foods;
    }

    private User getUser() {
        Address address = new Address("도시", "주소", "집");
        User user = User.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address).
                phone("010-1111-1111").
                grade(GRADE.VIP).
                build();

        userService.save(user);

        return user;
    }
}