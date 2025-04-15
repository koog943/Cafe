package cafe.food.domain.food;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("drink")
public class Drink extends Food{

//    @Enumerated(EnumType.STRING)
//    private DrinkSize size;
//
//    @Enumerated(EnumType.STRING)
//    private DrinkTemperature temperature;

}
