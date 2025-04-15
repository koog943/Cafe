package cafe.food.repository;

import cafe.food.domain.Order;
import cafe.food.domain.Status;
import cafe.food.domain.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cafe.food.domain.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<Order> findByMemberName(String name) {
        List<Order> orders = query.selectFrom(order)
                .join(order.member, QMember.member)
                .fetchJoin()
                .where(order.member.name.eq(name))
                .fetch();

        return orders;
    }

    @Override
    public List<Order> findByMemberNameAndStatus(String name, Status status) {
        List<Order> orders = query.selectFrom(order)
                .join(order.member, QMember.member)
                .fetchJoin()
                .where(order.member.name.eq(name))
                .where(order.orderStatus.eq(status))
                .fetch();

        return orders;
    }
}
