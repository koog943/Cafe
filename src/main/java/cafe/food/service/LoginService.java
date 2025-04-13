package cafe.food.service;

import cafe.food.config.QueryDslConfig;
import cafe.food.domain.member.Member;
import cafe.food.repository.MemberRepository;
import cafe.food.repository.MemberRepositoryImpl;
import cafe.food.request.LoginForm;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepositoryImpl memberRepository;

    public Member login(LoginForm loginForm) {
        Member login = memberRepository.login(loginForm);
        return login;
    }

}
