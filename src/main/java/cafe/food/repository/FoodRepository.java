package cafe.food.repository;

import cafe.food.domain.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);

    @Query("SELECT f FROM Food f LEFT JOIN FETCH f.orderFoods WHERE f.name = :name")
    Optional<Food> findByNameWithOrderFood(@Param("name") String name);
}
