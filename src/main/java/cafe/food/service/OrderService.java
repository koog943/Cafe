package cafe.food.service;

import cafe.food.domain.Order;
import cafe.food.domain.food.Food;
import cafe.food.domain.food.OrderFood;
import cafe.food.domain.member.Member;
import cafe.food.domain.status;
import cafe.food.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order order(Member member, Food food) {
        Order order = Order.builder()
                .member(member)
                .build();

        OrderFood orderFood = OrderFood.builder()
                .order(order)
                .food(food)
                .build();

        order.addOrderFood(orderFood);
        member.addOrder(order);

        orderRepository.save(order);
        return order;
    }

    public Order order(Member member, List<Food> foods) {
        Order order = Order.builder()
                .member(member)
                .build();


        foods.stream().forEach(food -> {
            OrderFood orderFood = OrderFood.builder()
                    .order(order)
                    .food(food)
                    .build();
            order.addOrderFood(orderFood);
        });

        member.addOrder(order);
        orderRepository.save(order);
        return order;
    }

    public Order findByMember(Member member) {
        return orderRepository.findByMember(member).orElse(null);
    }

    public Order cancelOrder(Order order) {
        order.setOrderStatus(status.CANCEL);
        return order;
    }

}
