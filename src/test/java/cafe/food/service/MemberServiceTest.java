package cafe.food.service;

import cafe.food.domain.member.Address;
import cafe.food.domain.member.GRADE;
import cafe.food.domain.member.Member;
import cafe.food.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


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

        assertThat(member).isEqualTo(find);
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

        Long saveId = memberService.save(member);

        Member find = memberService.findByName(member.getName());

        assertThat(member).isEqualTo(find);
    }
}