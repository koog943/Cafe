package cafe.food.controller;

import cafe.food.domain.Order;
import cafe.food.domain.SessionConst;
import cafe.food.domain.food.Food;
import cafe.food.domain.member.Member;
import cafe.food.request.OrderForm;
import cafe.food.response.ResOrder;
import cafe.food.service.FoodService;
import cafe.food.service.MemberService;
import cafe.food.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cafe.food.domain.SessionConst.LOGIN_MEMBER;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final FoodService foodService;
    private final MemberService memberService;

    @GetMapping("/order")
    public String orderForm(@SessionAttribute(name = LOGIN_MEMBER)Member member, Model model) {
        if (member == null) {
            return "/home";
        }
        OrderForm orderForm = new OrderForm();
        orderForm.setMemberName(member.getName());
        List<Food> foods = foodService.foodList();
        model.addAttribute("form", orderForm);
        model.addAttribute("foods", foods);

        return "/order/orderForm";
    }

    @PostMapping("/order")
    @ResponseBody
    public String order(@ModelAttribute(name = "orderForm") OrderForm form) {
        Member member = memberService.findByName(form.getMemberName());
        Food food = foodService.findById(form.getFoodId());
        Order order = orderService.order(member, food);

        return form.toString(food.getName());
    }



}
