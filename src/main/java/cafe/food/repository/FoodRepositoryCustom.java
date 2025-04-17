package cafe.food.repository;

import cafe.food.domain.food.Food;
import cafe.food.response.ResFood;

public interface FoodRepositoryCustom {
    Long editName(Long id, String name);

    Long editPrice(Long id, int price);
}
