package cafe.food.service;

import cafe.food.domain.Order;
import cafe.food.domain.Status;
import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.domain.food.OrderFood;
import cafe.food.domain.member.Address;
import cafe.food.domain.member.Member;
import cafe.food.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    EntityManager em;

    @BeforeEach
    void clear() {
        orderRepository.deleteAll();
    }

    @Test
    @Transactional(readOnly = true)
    void order() {
        Member member = getMember();
        Food dessert = getFood();

        //영속성 컨텍스트를 비움
        em.clear();

        //member != findMember, dessert != findDessert
        Member findMember = memberService.findById(member.getId());
        Food findDeesert = foodService.findById(dessert.getId());

        int count = 3;
        Order order = orderService.order(findMember, findDeesert, count);

        Order findOrder = orderRepository.findById(order.getId()).orElse(null);
        OrderFood findOrderFood = findOrder.getOrderFoods().get(0);

        assertThat(findOrder.getId()).isEqualTo(findOrder.getId());
        assertThat(findOrderFood.getFood().getName()).isEqualTo(dessert.getName());

        assertThat(findOrderFood.getCount()).isEqualTo(count);
        assertThat(findOrderFood.getFood().getQuantity()).isEqualTo(dessert.getQuantity() - count);
        assertThat(findOrderFood.getTotalPrice()).isEqualTo(dessert.getPrice() * count);

        assertThat(findOrder.getMember().getId()).isEqualTo(member.getId());
    }


    @Test
    void cancel() {
        Member member = getMember();
        Food food = getFood();

        Order order = orderService.order(member, food, 3);
        orderService.cancelOrder(order.getId());

        Order cancelOrder = orderRepository.findById(order.getId()).orElse(null);
        assertThat(cancelOrder.getOrderStatus()).isEqualTo(Status.CANCEL);
    }

    @Test
    void findByStatusList() {
        Member member = getMember();
        Food food = getFood();

        Order order = orderService.order(member, food, 3);
        orderService.order(member, food, 4);
        orderService.order(member, food, 5);

        orderService.cancelOrder(order.getId());

        List<Order> listOrder = orderService.findByStatus(Status.ORDER);
        List<Order> liSTCancel = orderService.findByStatus(Status.CANCEL);

        assertThat(listOrder.iterator().next().getOrderStatus()).isEqualTo(Status.ORDER);
        assertThat(listOrder.size()).isEqualTo(2);

        assertThat(liSTCancel.iterator().next().getOrderStatus()).isEqualTo(Status.CANCEL);
        assertThat(liSTCancel.size()).isEqualTo(1);
    }

    @Test
    @Transactional(readOnly = true)
    void findByMemberNameAndStatusList() {
        Member member = getMember();

        Food food = getFood();
        memberService.save(member);

        Order order1 = orderService.order(member, food, 3);
        orderService.order(member, food, 4);
        orderService.order(member, food, 5);
        orderService.cancelOrder(order1.getId());

        em.flush();
        em.clear();

        List<Order> listOrder = orderService.findByMemberNameAndStatus(member.getName(), Status.ORDER);
        List<Order> listCancel = orderService.findByMemberNameAndStatus(member.getName(), Status.CANCEL);
        List<Order> zeroSize = orderService.findByMemberNameAndStatus("", Status.CANCEL);

        assertThat(listOrder.iterator().next().getOrderStatus()).isEqualTo(Status.ORDER);
        assertThat(listOrder.iterator().next().getMember().getName()).isEqualTo(member.getName());
        assertThat(listOrder.size()).isEqualTo(2);

        assertThat(listCancel.iterator().next().getOrderStatus()).isEqualTo(Status.CANCEL);
        assertThat(listCancel.iterator().next().getMember().getName()).isEqualTo(member.getName());
        assertThat(listCancel.size()).isEqualTo(1);

        assertThat(zeroSize.size()).isEqualTo(0);
    }

    @Test
    @Transactional(readOnly = true)
    void findMemberNameOrderList() {
        Member member = getMember();
        Food food = getFood();

        orderService.order(member, food, 3);
        orderService.order(member, food, 4);
        orderService.order(member, food, 5);
        List<Order> list = orderService.findByMemberName(member.getName());

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.iterator().next().getMember().getName()).isEqualTo(member.getName());
    }

    private Food getFood() {
        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(100)
                .build();

        foodService.save(dessert);
        return dessert;
    }


    private Member getMember() {
        Address address = new Address("도시1", "주소1", "집1");
        Member member = Member.builder().
                email("qwe").
                password("qwe").
                name("user1").
                address(address).
                build();

        memberService.save(member);
        return member;
    }

}