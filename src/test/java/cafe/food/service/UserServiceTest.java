package cafe.food.service;

import cafe.food.domain.member.Address;
import cafe.food.domain.member.Admin;
import cafe.food.domain.member.GRADE;
import cafe.food.domain.member.User;
import cafe.food.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
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

        Long saveId = userService.save(user);

        User find = userRepository.findById(saveId).orElse(null);

        assertThat(user).isEqualTo(find);
    }

    @Test
    void findByName() {
        Address address = new Address("도시", "주소", "집");
        User user = User.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address).
                phone("010-1111-1111").
                grade(GRADE.VIP).
                build();

        Long saveId = userService.save(user);

        User find = userService.findByName(user.getName());

        assertThat(user).isEqualTo(find);
    }
}