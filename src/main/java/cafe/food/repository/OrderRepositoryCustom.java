package cafe.food.repository;


import cafe.food.domain.Order;
import cafe.food.domain.Status;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findByMemberName(String name);
    List<Order> findByMemberNameAndStatus(String name, Status status);
}
