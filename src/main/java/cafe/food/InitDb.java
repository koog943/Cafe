package cafe.food;

import cafe.food.domain.member.Address;
import cafe.food.domain.member.Admin;
import cafe.food.domain.member.GRADE;
import cafe.food.domain.member.User;
import cafe.food.repository.AdminRepository;
import cafe.food.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class InitDb {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;

//    @PostConstruct
    private void init1() {
        Address address = new Address("도시", "주소", "집");
        User user = User.builder().
                email("mail1").
                password("pw1").
                name("user1").
                address(address).
                phone("010-1111-1111").
                grade(GRADE.VIP).
                build();

        userRepository.save(user);
    }
}
