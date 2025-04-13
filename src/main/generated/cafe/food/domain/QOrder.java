package cafe.food.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -169006333L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final DateTimePath<java.time.LocalDateTime> createAT = createDateTime("createAT", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final cafe.food.domain.member.QMember member;

    public final ListPath<cafe.food.domain.food.OrderFood, cafe.food.domain.food.QOrderFood> orderFoods = this.<cafe.food.domain.food.OrderFood, cafe.food.domain.food.QOrderFood>createList("orderFoods", cafe.food.domain.food.OrderFood.class, cafe.food.domain.food.QOrderFood.class, PathInits.DIRECT2);

    public final NumberPath<Integer> orderPrice = createNumber("orderPrice", Integer.class);

    public final EnumPath<status> orderStatus = createEnum("orderStatus", status.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new cafe.food.domain.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

