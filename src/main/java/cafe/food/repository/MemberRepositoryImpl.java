package cafe.food.repository;

import cafe.food.domain.member.Member;
import cafe.food.domain.member.QMember;
import cafe.food.request.LoginForm;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static cafe.food.domain.member.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory query;

    public Member login(LoginForm form) {
        return query.selectFrom(member)
                .where(member.email.eq(form.getEmail()))
                .where(member.password.eq(form.getPassword()))
                .fetchOne();
    }


}
