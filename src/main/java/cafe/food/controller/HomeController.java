package cafe.food.controller;

import cafe.food.domain.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static cafe.food.domain.SessionConst.LOGIN_MEMBER;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute(LOGIN_MEMBER) != null) {
            return "/loginHome";
        }

        return "/home";
    }

}
