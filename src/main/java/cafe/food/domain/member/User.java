package cafe.food.domain.member;

import cafe.food.domain.Order;
import cafe.food.domain.food.OrderFood;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private GRADE grade;

    @Embedded
    private Address address;

    @Builder
    public User(String name, String email, String password, String phone, GRADE grade, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.grade = grade;
        this.address = address;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
