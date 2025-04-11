package cafe.food.service;

import cafe.food.domain.member.User;
import cafe.food.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(User user) {
        User save = userRepository.save(user);
        return save.getId();
    }

    public User findByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }


}
