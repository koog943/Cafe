package cafe.food.service;

import cafe.food.domain.QOrder;
import cafe.food.domain.member.Member;
import cafe.food.domain.member.QMember;
import cafe.food.repository.MemberRepository;
import cafe.food.repository.MemberRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cafe.food.domain.QOrder.order;
import static cafe.food.domain.member.QMember.member;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
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

    public Member findByNameWithOrders(String name) {
        return memberRepository.findByNameWithOrders(name);
    }


}
