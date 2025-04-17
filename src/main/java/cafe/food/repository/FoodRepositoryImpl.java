package cafe.food.repository;

import cafe.food.domain.food.Food;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.parser.Entity;

import static cafe.food.domain.food.QFood.food;

@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryCustom{

    private final JPAQueryFactory query;
    private final EntityManager em;

    @Override
    public Long editName(Long id, String name) {
        long execute = query.update(food)
                .set(food.name, name)
                .where(food.id.eq(id))
                .execute();
        em.clear();
        em.flush();
        return execute;
    }

    @Override
    public Long editPrice(Long id, int price) {
        long execute = query.update(food)
                .set(food.price, price)
                .where(food.id.eq(id))
                .execute();
        em.clear();
        em.flush();

        return execute;
    }
}
