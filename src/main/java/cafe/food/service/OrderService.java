package cafe.food.service;

import cafe.food.domain.Order;
import cafe.food.domain.food.Food;
import cafe.food.domain.food.OrderFood;
import cafe.food.domain.member.Member;
import cafe.food.domain.Status;
import cafe.food.repository.OrderRepository;
import cafe.food.repository.OrderRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order order(Member member, Food food, int count) {
        Order order = Order.builder()
                .member(member)
                .build();

        OrderFood orderFood = OrderFood.builder()
                .order(order)
                .food(food)
                .count(count)
                .build();

        order.addOrderFood(orderFood);
        member.addOrder(order);

        orderRepository.save(order);
        return order;
    }

    @Transactional
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

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> orderList() {
        return orderRepository.findAll();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        order.setOrderStatus(Status.CANCEL);
    }

    public List<Order> findByStatus(Status status) {
        return orderRepository.findByOrderStatus(status);
    }

    public List<Order> findByMemberName(String name) {
        return orderRepository.findByMemberName(name);
    }

    public List<Order> findByMemberNameAndStatus(String name, Status status) {
        return orderRepository.findByMemberNameAndStatus(name, status);
    }

}
