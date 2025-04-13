package cafe.food.domain.food;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderFood is a Querydsl query type for OrderFood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderFood extends EntityPathBase<OrderFood> {

    private static final long serialVersionUID = 1396948711L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderFood orderFood = new QOrderFood("orderFood");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final QFood food;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final cafe.food.domain.QOrder order;

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QOrderFood(String variable) {
        this(OrderFood.class, forVariable(variable), INITS);
    }

    public QOrderFood(Path<? extends OrderFood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderFood(PathMetadata metadata, PathInits inits) {
        this(OrderFood.class, metadata, inits);
    }

    public QOrderFood(Class<? extends OrderFood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new QFood(forProperty("food")) : null;
        this.order = inits.isInitialized("order") ? new cafe.food.domain.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

