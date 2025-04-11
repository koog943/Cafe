package cafe.food.repository;

import cafe.food.domain.member.Address;
import cafe.food.domain.member.Admin;
import cafe.food.domain.member.GRADE;
import cafe.food.domain.member.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    void save() {
        Address address = new Address("도시", "주소", "집");
        User user = User.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address).
                phone("010-1111-1111").
                grade(GRADE.VIP).
                build();

        Admin admin = Admin.builder()
                .email("mail2")
                .password("pw2")
                .name("admin1")
                .build();

        userRepository.save(user);
        adminRepository.save(admin);

        Admin findAdmin = adminRepository.findById(1L).orElse(null);
        User findUser = userRepository.findById(1L).orElse(null);

        Assertions.assertThat(findAdmin).isEqualTo(admin);
        Assertions.assertThat(findUser).isEqualTo(user);

    }
}
