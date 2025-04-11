package cafe.food.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Builder
    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
