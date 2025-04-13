package cafe.food.domain.food;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDessert is a Querydsl query type for Dessert
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDessert extends EntityPathBase<Dessert> {

    private static final long serialVersionUID = 84333569L;

    public static final QDessert dessert = new QDessert("dessert");

    public final QFood _super = new QFood(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final ListPath<OrderFood, QOrderFood> orderFood = _super.orderFood;

    //inherited
    public final NumberPath<Integer> price = _super.price;

    //inherited
    public final NumberPath<Integer> quantity = _super.quantity;

    public QDessert(String variable) {
        super(Dessert.class, forVariable(variable));
    }

    public QDessert(Path<? extends Dessert> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDessert(PathMetadata metadata) {
        super(Dessert.class, metadata);
    }

}

