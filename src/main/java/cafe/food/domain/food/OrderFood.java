package cafe.food.domain.food;

import cafe.food.domain.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    @NotNull
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;

    @Min(value = 1)
    private int count;

    @Min(value = 100)
    private int totalPrice;

    @Builder
    public OrderFood(Food food, Order order, int count) {
        this.food = food;
        this.order = order;
        this.count = count;
        this.totalPrice = food.getPrice() * count;
    }
}
