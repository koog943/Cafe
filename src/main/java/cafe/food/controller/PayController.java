package cafe.food.controller;

import cafe.food.domain.member.Member;
import cafe.food.response.PayApproveRes;
import cafe.food.service.PayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Enumeration;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PayController {

    private final PayService payService;

    @GetMapping("/payment/approval")
    public String payApprove(@RequestParam("pg_token") String pg_Token, HttpServletRequest req) throws JsonProcessingException {
        HttpSession session = req.getSession();


        String tid = (String) session.getAttribute("tid");
        ResponseEntity<PayApproveRes> payApproveResResponseEntity = payService.callApproveApi(pg_Token, tid);

        Member member = (Member)session.getAttribute("loginMember");
        String name = member.getName();

        return "redirect:/orders?memberName=" + name + "&status=ORDER";
    }


}
