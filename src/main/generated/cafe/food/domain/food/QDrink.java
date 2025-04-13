package cafe.food.domain.food;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDrink is a Querydsl query type for Drink
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrink extends EntityPathBase<Drink> {

    private static final long serialVersionUID = 590408755L;

    public static final QDrink drink = new QDrink("drink");

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

    public QDrink(String variable) {
        super(Drink.class, forVariable(variable));
    }

    public QDrink(Path<? extends Drink> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDrink(PathMetadata metadata) {
        super(Drink.class, metadata);
    }

}

