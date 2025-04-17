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
@ActiveProfiles("test")
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
                .count(2)
                .build();
        // 10000

        OrderFood orderDrink = OrderFood.builder()
                .food(drink)
                .order(order)
                .count(5)
                .build();
        // 15000

        order.addOrderFood(orderDessert);
        order.addOrderFood(orderDrink);

        Order save = orderRepository.save(order);

        Order findOrder = orderRepository.findById(save.getId()).orElse(null);

        assertThat(findOrder.getId()).isEqualTo(save.getId());
        assertThat(findOrder.getMember().getId()).isEqualTo(save.getMember().getId());
        assertThat(findOrder.getOrderFoods().size()).isEqualTo(save.getOrderFoods().size());
    }

    private Drink saveDrink() {
        Drink dirnk = Drink.builder()
                .name("아메리카노")
                .price(30000)
                .quantity(5)
                .build();

        foodRepository.save(dirnk);
        return dirnk;
    }

    private Dessert saveDessert() {
        Dessert dessert = Dessert.builder()
                .name("케이크")
                .price(50000)
                .quantity(2)
                .build();

        foodRepository.save(dessert);
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

        memberRepository.save(member);
        return member;
    }
}