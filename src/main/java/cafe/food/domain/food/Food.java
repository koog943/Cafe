package cafe.food.domain.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
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

    @NotBlank
    private String name;

    @Min(value = 100)
    private int price;

    @Min(value = 1)
    private int quantity;

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "food", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderFood> orderFoods = new ArrayList<>();

    public void stockQuantity(int count) {
        this.quantity -= count;
    }

}
