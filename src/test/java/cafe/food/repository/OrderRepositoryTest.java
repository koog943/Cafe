package cafe.food.repository;

import cafe.food.domain.Order;
import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.OrderFood;
import cafe.food.domain.member.Address;
import cafe.food.domain.member.GRADE;
import cafe.food.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderFoodRepository orderFoodRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void order() {
        Member member = saveMember();
        Drink drink = saveDrink();
        Dessert dessert = saveDessert();

        Order order = Order.builder()
                .member(member)
                .build();

        OrderFood orderDessert = OrderFood.builder()
                .food(dessert)
                .order(order)
                .build();
        // 10000

        OrderFood orderDrink = OrderFood.builder()
                .food(drink)
                .order(order)
                .build();
        // 15000

        order.addOrderFood(orderDessert);
        order.addOrderFood(orderDrink);

        orderRepository.save(order);

        Order findOrder = orderRepository.findById(1L).orElse(null);



        List<Order> orders = member.getOrders();

        System.out.println("====================");
        for (Order order1 : orders) {
            System.out.println("order1 = " + order1);
        }


    }

    private Drink saveDrink() {
        Drink dirnk = Drink.builder()
                .name("아메리카노")
                .price(3000)
                .quantity(5)
                .build();

        return dirnk;
    }

    private Dessert saveDessert() {
        Dessert dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(2)
                .build();

        return dessert;
    }

    private Member saveMember() {
        Address address = new Address("도시", "주소", "집");
        Member member = Member.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address).
                build();

        return member;
    }
}