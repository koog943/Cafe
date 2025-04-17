package cafe.food.repository;

import cafe.food.domain.Order;
import cafe.food.domain.Status;
import cafe.food.domain.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cafe.food.domain.QOrder.order;
import static cafe.food.domain.member.QMember.member;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Order> findByMemberNameAndStatus(String name, Status status) {
        List<Order> orders = query.selectFrom(order)
                .leftJoin(member).on(member.id.eq(order.member.id))
                .where(member.name.eq(name))
                .where(order.orderStatus.eq(status))
                .fetch();

        return orders;
    }

    @Override
    public List<Order> findByStatus(Status status) {
        List<Order> orders = query.selectFrom(order)
                .where(order.orderStatus.eq(status))
                .fetch();

        return orders;
    }

    @Override
    public List<Order> findByMemberName(String name) {
        List<Order> orders = query.selectFrom(order)
                .join(order.member, member)
                .where(order.member.name.eq(name))
                .fetch();

        return orders;
    }
}
