package cafe.food.controller;

import cafe.food.domain.Order;
import cafe.food.domain.Status;
import cafe.food.domain.food.Food;
import cafe.food.domain.member.Member;
import cafe.food.request.OrderForm;
import cafe.food.request.OrderSearchForm;
import cafe.food.service.FoodService;
import cafe.food.service.MemberService;
import cafe.food.service.OrderService;
import lombok.RequiredArgsConstructor;
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
        int count = form.getCount();
        Order order = orderService.order(member, food, count);

        return form.toString(food.getName());
    }

//    @GetMapping("/orders")
    public String orderList(Model model) {
        List<Order> orderList = orderService.orderList();
        model.addAttribute("orders", orderList);
        model.addAttribute("orderSearch", new OrderSearchForm());

        return "/order/orderList";
    }

    @PostMapping("/orders/{id}/cancel")
    public String orderCancel(@PathVariable(name = "id") Long id, Model model) {
        Order order = orderService.findById(id);
        orderService.cancelOrder(order.getId());

        List<Order> orderList = orderService.orderList();
        model.addAttribute("orders", orderList);
        model.addAttribute("orderSearch", new OrderSearchForm());

        return "/order/orderList";
    }

    @GetMapping("/orders")
    public String searchOrder(@RequestParam(name = "memberName", required = false) String memberName,
                              @RequestParam(name = "status", required = false) Status status,
                              Model model) {
        System.out.println("memberName = " + memberName);
        System.out.println("status = " + status);

        if(memberName == null) {
            List<Order> orders = orderService.orderList();
            model.addAttribute("orders", orders);
            model.addAttribute("orderSearch", new OrderSearchForm());
            return "/order/orderList";
        }


        if (!memberName.equals("") && status != null) {
            List<Order> orders = orderService.findByMemberNameAndStatus(memberName, status);
            model.addAttribute("orders", orders);
            model.addAttribute("orderSearch", new OrderSearchForm());
        } else if (!memberName.equals("")) {
            List<Order> orders = orderService.findByMemberName(memberName);
            model.addAttribute("orders", orders);
            model.addAttribute("orderSearch", new OrderSearchForm());
        } else if (status != null) {
            List<Order> orders = orderService.findByStatus(status);
            model.addAttribute("orders", orders);
            model.addAttribute("orderSearch", new OrderSearchForm());
        } else {
            List<Order> orders = orderService.orderList();
            model.addAttribute("orders", orders);
            model.addAttribute("orderSearch", new OrderSearchForm());
        }

        return "/order/orderList";
    }



}
