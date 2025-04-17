package cafe.food.domain.member;

import cafe.food.domain.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private GRADE grade = GRADE.NO_VIP;

    @Embedded
    private Address address;

    @Builder
    public Member(String name, String email, String password, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
