package cafe.food.response;

import cafe.food.domain.member.Address;
import cafe.food.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResMember {

    private String name;

    private String email;

    private Address address = new Address();

    public ResMember(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.address.setStreet(member.getAddress().getStreet());
        this.address.setCity(member.getAddress().getCity());
        this.address.setZip(member.getAddress().getZip());
    }
}
