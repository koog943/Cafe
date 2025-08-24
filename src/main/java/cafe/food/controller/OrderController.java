package cafe.food.controller;

import cafe.food.domain.Order;
import cafe.food.domain.Status;
import cafe.food.domain.food.Food;
import cafe.food.domain.member.Member;
import cafe.food.request.LoginForm;
import cafe.food.request.OrderForm;
import cafe.food.request.OrderSearchForm;
import cafe.food.request.PayApproveReq;
import cafe.food.response.PayRes;
import cafe.food.service.FoodService;
import cafe.food.service.MemberService;
import cafe.food.service.OrderService;
import cafe.food.service.PayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static cafe.food.domain.SessionConst.LOGIN_MEMBER;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final FoodService foodService;
    private final MemberService memberService;
    private final PayService payService;

    @GetMapping("/order")
    public String orderForm(@SessionAttribute(name = LOGIN_MEMBER)Member member,
                            Model model) {
        OrderForm orderForm = new OrderForm();
        orderForm.setMemberName(member.getName());
        List<Food> foods = foodService.foodList();
        model.addAttribute("form", orderForm);
        model.addAttribute("foods", foods);

        return "/order/orderForm";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute(name = "orderForm") OrderForm form, HttpServletRequest req) throws JsonProcessingException {
        Member member = memberService.findByName(form.getMemberName());
        Food food = foodService.findById(form.getFoodId());
        int count = form.getCount();

        ResponseEntity<PayRes> payResResponseEntity = payService.callReadyApi(member, food, count);
        HttpSession session = req.getSession();
        session.setAttribute("tid", payResResponseEntity.getBody().getTid());
        String nextRedirectPcUrl = payResResponseEntity.getBody().getNext_redirect_pc_url();

        orderService.order(member, food, count);
        return "redirect:" + nextRedirectPcUrl;
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
            Collections.reverse(orders);
            model.addAttribute("orders", orders);
            model.addAttribute("orderSearch", new OrderSearchForm());
        } else if (!memberName.equals("")) {
            List<Order> orders = orderService.findByMemberName(memberName);
            Collections.reverse(orders);
            model.addAttribute("orders", orders);
            model.addAttribute("orderSearch", new OrderSearchForm());
        } else if (status != null) {
            List<Order> orders = orderService.findByStatus(status);
            Collections.reverse(orders);
            model.addAttribute("orders", orders);
            model.addAttribute("orderSearch", new OrderSearchForm());
        } else {
            List<Order> orders = orderService.orderList();
            Collections.reverse(orders);
            model.addAttribute("orders", orders);
            model.addAttribute("orderSearch", new OrderSearchForm());
        }

        return "/order/orderList";
    }



}
