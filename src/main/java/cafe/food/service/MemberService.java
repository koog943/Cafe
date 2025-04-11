package cafe.food.service;

import cafe.food.domain.member.Member;
import cafe.food.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;

    public Long save(Member member) {
        Member save = memberRepository.save(member);
        return save.getId();
    }

    public Member findByName(String name) {
        return memberRepository.findByName(name).orElse(null);
    }


}
