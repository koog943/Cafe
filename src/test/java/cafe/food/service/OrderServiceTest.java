package cafe.food.service;

import cafe.food.domain.Order;
import cafe.food.domain.Status;
import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.domain.member.Address;
import cafe.food.domain.member.Member;
import cafe.food.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    FoodService foodService;

    @Test
    void order() {
        Address address1 = new Address("도시1", "주소1", "집1");
        Member member1 = Member.builder().
                email("qwe").
                password("qwe").
                name("user1").
                address(address1).
                build();
        memberService.save(member1);

        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(100)
                .build();
        foodService.save(dessert);

        Order order = orderService.order(member1, dessert, 3);

        Order findOrder = orderRepository.findById(order.getId()).orElse(null);
        assertThat(findOrder).isNotNull();
    }

    @Test
    void orders() {
        Member member = getUser();
        List<Food> foods = getFoods();

        Order order = orderService.order(member, foods);
        Order findOrder = orderRepository.findById(order.getId()).orElse(null);

        assertThat(findOrder).isNotNull();
    }

    @Test
    void cancel() {
        Member member = getUser();
        List<Food> foods = getFoods();

        Order order = orderService.order(member, foods);
        orderService.cancelOrder(order.getId());

        Order cancelOrder = orderRepository.findById(order.getId()).orElse(null);
        assertThat(cancelOrder.getOrderStatus()).isEqualTo(Status.CANCEL);
    }

    @Test
    void findByStatusList() {
        Member member = getUser();
        List<Food> foods = getFoods();

        orderService.order(member, foods);
        orderService.order(member, foods);
        Order order = orderService.order(member, foods);
        orderService.cancelOrder(order.getId());


        List<Order> listOrder = orderService.findByStatus(Status.ORDER);
        List<Order> liSTCancel = orderService.findByStatus(Status.CANCEL);

        assertThat(listOrder.iterator().next().getOrderStatus()).isEqualTo(Status.ORDER);
        assertThat(listOrder.size()).isEqualTo(2);

        assertThat(liSTCancel.iterator().next().getOrderStatus()).isEqualTo(Status.CANCEL);
        assertThat(liSTCancel.size()).isEqualTo(1);
    }

    @Test
    void findByMemberNameAndStatusList() {
        Member member = getUser();
        List<Food> foods = getFoods();

        orderService.order(member, foods);
        orderService.order(member, foods);
        Order order = orderService.order(member, foods);
        orderService.cancelOrder(order.getId());

        List<Order> listOrder = orderService.findByMemberNameAndStatus(order.getMember().getName(), Status.ORDER);
        List<Order> listCancel = orderService.findByMemberNameAndStatus(order.getMember().getName(), Status.CANCEL);
        List<Order> zeroSize = orderService.findByMemberNameAndStatus("", Status.CANCEL);


        assertThat(listOrder.iterator().next().getOrderStatus()).isEqualTo(Status.ORDER);
        assertThat(listOrder.iterator().next().getMember().getName()).isEqualTo(order.getMember().getName());
        assertThat(listOrder.size()).isEqualTo(2);

        assertThat(listCancel.iterator().next().getOrderStatus()).isEqualTo(Status.CANCEL);
        assertThat(listCancel.iterator().next().getMember().getName()).isEqualTo(order.getMember().getName());
        assertThat(listCancel.size()).isEqualTo(1);

        assertThat(zeroSize.size()).isEqualTo(0);

    }

    @Test
    void findMemberNameOrderList() {
        Member member = getUser();
        List<Food> foods = getFoods();

        orderService.order(member, foods);
        orderService.order(member, foods);
        orderService.order(member, foods);

        List<Order> list = orderService.findByMemberName(member.getName());

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.iterator().next().getMember().getName()).isEqualTo(member.getName());
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

        foodService.save(dessert);
        foodService.save(drink);
        return foods;
    }

    private Member getUser() {
        Address address = new Address("도시", "주소", "집");
        Member member = Member.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address).
                build();

        memberService.save(member);

        return member;
    }
}