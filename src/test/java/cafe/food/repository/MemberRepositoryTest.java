package cafe.food.repository;

import cafe.food.domain.member.Address;
import cafe.food.domain.member.Admin;
import cafe.food.domain.member.GRADE;
import cafe.food.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

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

        Admin admin = Admin.builder()
                .email("mail2")
                .password("pw2")
                .name("admin1")
                .build();

        memberRepository.save(member);
        adminRepository.save(admin);

        Admin findAdmin = adminRepository.findById(1L).orElse(null);
        Member findMember = memberRepository.findById(1L).orElse(null);

        Assertions.assertThat(findAdmin).isEqualTo(admin);
        Assertions.assertThat(findMember).isEqualTo(member);

    }
}
