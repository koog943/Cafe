package cafe.food.service;

import cafe.food.domain.member.Address;
import cafe.food.domain.member.GRADE;
import cafe.food.domain.member.Member;
import cafe.food.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void clear() {
        memberRepository.deleteAll();
    }

    @Test
    void save() {
        Address address = new Address("도시", "주소", "집");
        Member member = Member.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address).
                build();

        Long saveId = memberService.save(member);
        Member find = memberRepository.findById(saveId).orElse(null);

        assertThat(find).isNotNull();
    }

    @Test
    void findByName() {
        Address address = new Address("도시", "주소", "집");
        Member member = Member.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address).
                build();

        memberService.save(member);
        Member find = memberService.findByName(member.getName());

        assertThat(member.getName()).isEqualTo(find.getName());
    }

    @Test
    void findMembers() {
        Address address1 = new Address("도시1", "주소1", "집1");
        Member member1 = Member.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address1).
                build();

        Address address2 = new Address("도시2", "주소2", "집2");
        Member member2 = Member.builder().
                email("mail2").
                password("pw2").
                name("user2").
                address(address2).
                build();

        memberService.save(member1);
        memberService.save(member2);

        List<Member> members = memberService.findMembers();
        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    void findByNameWithOrders() {
        Address address = new Address("도시1", "주소1", "집1");
        Member member = Member.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address).
                build();

        memberService.save(member);

        Member findMember = memberService.findByNameWithOrders(member.getName());

        assertThat(findMember.getOrders()).isNotNull();
    }


}