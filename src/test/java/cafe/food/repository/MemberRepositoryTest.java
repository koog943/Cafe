package cafe.food.repository;

import cafe.food.domain.member.Address;
import cafe.food.domain.member.GRADE;
import cafe.food.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
public class MemberRepositoryTest {

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

        Member save = memberRepository.save(member);

        Member findMember = memberRepository.findById(save.getId()).orElse(null);

        Assertions.assertThat(findMember.getId()).isEqualTo(save.getId());

    }
}
