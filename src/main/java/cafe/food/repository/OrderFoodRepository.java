package cafe.food.repository;

import cafe.food.domain.food.OrderFood;
import cafe.food.domain.member.Member;
import cafe.food.request.LoginForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
}
