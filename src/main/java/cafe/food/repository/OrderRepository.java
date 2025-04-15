package cafe.food.repository;

import cafe.food.domain.Order;
import cafe.food.domain.Status;
import cafe.food.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    Optional<Order> findByMember(Member member);
    List<Order> findByOrderStatus(Status orderStatus);
}
