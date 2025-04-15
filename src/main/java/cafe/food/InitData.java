package cafe.food;

import cafe.food.domain.food.Dessert;
import cafe.food.domain.food.Drink;
import cafe.food.domain.food.Food;
import cafe.food.domain.member.Address;
import cafe.food.domain.member.Member;
import cafe.food.service.FoodService;
import cafe.food.service.MemberService;
import cafe.food.service.OrderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class InitData {

    private final MemberService memberService;
    private final FoodService foodService;
    private final OrderService orderService;

    @PostConstruct
    public void save() {
        saveMember();
        saveFood();
        saveOrder();
    }

    public void saveMember() {
        Address address1 = new Address("도시1", "주소1", "집1");
        Member member1 = Member.builder().
                email("qwe").
                password("qwe").
                name("user1").
                address(address1).
                build();

        Address address2 = new Address("도시2", "주소2", "집2");
        Member member2 = Member.builder().
                email("asd").
                password("asd").
                name("user2").
                address(address2).
                build();
        memberService.save(member1);
        memberService.save(member2);
    }

    public void saveFood() {
        Food dessert = Dessert.builder()
                .name("케이크")
                .price(5000)
                .quantity(100)
                .build();

        Food drink = Drink.builder()
                .name("커피")
                .price(3000)
                .quantity(200)
                .build();

        foodService.save(dessert);
        foodService.save(drink);
    }

    @Transactional
    public void saveOrder() {
        Member member1 = memberService.findByNameWithOrders("user1");
        Member member2 = memberService.findByNameWithOrders("user2");

        Food food1 = foodService.findByNameWithOrderFood("케이크");
        Food food2 = foodService.findByNameWithOrderFood("커피");

        orderService.order(member1, food1, 1);
        orderService.order(member1, food2, 3);
        orderService.order(member2, food1, 3);
        orderService.order(member2, food2, 1);

    }


}
