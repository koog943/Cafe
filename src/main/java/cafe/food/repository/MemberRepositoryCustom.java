package cafe.food.repository;

import cafe.food.domain.member.Member;
import cafe.food.request.LoginForm;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Member login(LoginForm form);
    Member findByNameWithOrders(String name);
}
