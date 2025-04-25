package cafe.food.request;

import cafe.food.domain.member.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "email을 입력해주세요")
    private String email;

    @NotEmpty(message = "password를 입력해주세요")
    private String password;

    private String city;

    private String zip;

    private String street;

}
