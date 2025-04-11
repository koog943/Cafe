package cafe.food.repository;

import cafe.food.domain.Order;
import cafe.food.domain.member.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser(User user);

}
