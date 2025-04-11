package cafe.food.service;

import cafe.food.domain.Order;
import cafe.food.domain.food.Food;
import cafe.food.domain.food.OrderFood;
import cafe.food.domain.member.User;
import cafe.food.domain.status;
import cafe.food.repository.FoodRepository;
import cafe.food.repository.OrderFoodRepository;
import cafe.food.repository.OrderRepository;
import cafe.food.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order order(User user, List<Food> foods) {
        Order order = Order.builder()
                .user(user)
                .build();


        foods.stream().forEach(food -> {
            OrderFood orderFood = OrderFood.builder()
                    .order(order)
                    .food(food)
                    .build();
            order.addOrderFood(orderFood);
        });

        user.addOrder(order);
        return order;
    }

    public Order findByUserOrder(User user) {
        return orderRepository.findByUser(user).orElse(null);
    }

    public Order cancelOrder(Order order) {
        order.setOrderStatus(status.CANCEL);
        return order;
    }

}
