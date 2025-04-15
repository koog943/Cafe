package cafe.food.domain.food;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@SuperBuilder
@Getter
@NoArgsConstructor
public abstract class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private int quantity;

    @OneToMany(mappedBy = "food", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderFood> orderFoods;

}
