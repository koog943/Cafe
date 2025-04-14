package cafe.food;

import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.domain.member.Address;
import cafe.food.domain.member.Member;
import cafe.food.service.FoodService;
import cafe.food.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

    private final MemberService memberService;
    private final FoodService foodService;

    @PostConstruct
    public void save() {
        saveMember();
        saveFood();
    }

    public void saveMember() {
        Address address = new Address("도시", "주소", "집");
        Member member = Member.builder().
                email("qwe").
                password("qwe").
                name("user1").
                address(address).
                build();

        memberService.save(member);
    }

    public void saveFood() {
        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(100)
                .build();

        Food drink = Drink.builder()
                .name("아메리카노")
                .price(3000)
                .quantity(200)
                .build();

        foodService.save(dessert);
        foodService.save(drink);
    }



}
