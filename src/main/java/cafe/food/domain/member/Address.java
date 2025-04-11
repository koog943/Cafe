package cafe.food.domain.member;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable // 사용하기위해서 빈생성자 필요함
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Address {

    private String city;

    private String zip;

    private String street;

}
