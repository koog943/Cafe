package cafe.food.service;

import cafe.food.domain.member.Member;
import cafe.food.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(Member member) {
        Member save = memberRepository.save(member);
        return save.getId();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member findByName(String name) {
        return memberRepository.findByName(name).orElse(null);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


}
