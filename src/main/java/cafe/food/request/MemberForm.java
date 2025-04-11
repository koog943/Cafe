package cafe.food.request;

import cafe.food.domain.member.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    private String name;

    private String email;

    private String password;

    private String city;

    private String zip;

    private String street;

}
