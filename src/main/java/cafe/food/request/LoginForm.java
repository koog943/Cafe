package cafe.food.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {

    @NotBlank(message = "email을 입력해주세요")
    private String email;

    @NotBlank(message = "password을 입력해주세요")
    private String password;

}
