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
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class InitData {

    private final MemberService memberService;
    private final FoodService foodService;
    private final OrderService orderService;
    private final EntityManager em;

    @PostConstruct
    public void save() {
        saveMember();
        saveFood();
        saveOrder1();
        saveOrder2();

        saveDessert();
        saveDrink();
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

    public void saveDessert() {
        List<Food> dessertList = Arrays.asList(
                Dessert.builder().name("딸기 타르트").price(6200).quantity(30).build(),
                Dessert.builder().name("마카롱").price(3000).quantity(200).build(),
                Dessert.builder().name("치즈케이크").price(5800).quantity(80).build(),
                Dessert.builder().name("푸딩").price(2500).quantity(150).build(),
                Dessert.builder().name("레몬 파이").price(4700).quantity(60).build(),
                Dessert.builder().name("티라미수").price(6000).quantity(45).build(),
                Dessert.builder().name("카라멜 브라우니").price(5200).quantity(70).build(),
                Dessert.builder().name("녹차 케이크").price(5300).quantity(90).build(),
                Dessert.builder().name("요거트 무스").price(4800).quantity(40).build()
        );

        dessertList.stream().forEach((desert)-> foodService.save(desert));
    }

    public void saveDrink() {
        List<Food> drinkList = Arrays.asList(
                Drink.builder().name("아이스티").price(2500).quantity(150).build(),
                Drink.builder().name("녹차").price(2700).quantity(100).build(),
                Drink.builder().name("콜라").price(2000).quantity(300).build(),
                Drink.builder().name("오렌지 주스").price(3500).quantity(120).build(),
                Drink.builder().name("레몬에이드").price(3800).quantity(90).build(),
                Drink.builder().name("카페라떼").price(4000).quantity(180).build(),
                Drink.builder().name("핫초코").price(3200).quantity(110).build(),
                Drink.builder().name("스무디").price(4500).quantity(70).build(),
                Drink.builder().name("에스프레소").price(2800).quantity(160).build()
        );

        drinkList.stream().forEach((drink)-> foodService.save(drink));

    }

    public void saveOrder1() {
        Member member1 = memberService.findByNameWithOrders("user1");
        Food food1 = foodService.findByNameWithOrderFood("케이크");
        Food food2 = foodService.findByNameWithOrderFood("커피");

        orderService.order(member1, food1, 2);
        orderService.order(member1, food2, 5);
    }

    public void saveOrder2() {
        Member member2 = memberService.findByNameWithOrders("user2");

        Food food1 = foodService.findByNameWithOrderFood("케이크");
        Food food2 = foodService.findByNameWithOrderFood("커피");

        orderService.order(member2, food1, 1);
        orderService.order(member2, food2, 3);
    }


}
