package cafe.food.service;

import cafe.food.domain.food.Food;
import cafe.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public Long save(Food food) {
        Food save = foodRepository.save(food);
        return save.getId();
    }

    public Food findByName(String name) {
        return foodRepository.findByName(name).orElse(null);
    }

}
