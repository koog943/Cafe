package cafe.food.domain.food;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = -257992349L;

    public static final QFood food = new QFood("food");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<OrderFood, QOrderFood> orderFoods = this.<OrderFood, QOrderFood>createList("orderFoods", OrderFood.class, QOrderFood.class, PathInits.DIRECT2);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QFood(String variable) {
        super(Food.class, forVariable(variable));
    }

    public QFood(Path<? extends Food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFood(PathMetadata metadata) {
        super(Food.class, metadata);
    }

}

