package cafe.food.domain.food;

import cafe.food.domain.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int count;

    private int totalPrice;

    @Builder
    public OrderFood(Food food, Order order) {
        this.food = food;
        this.order = order;
        this.count = food.getQuantity();
        this.totalPrice = food.getPrice() * count;
    }
}
