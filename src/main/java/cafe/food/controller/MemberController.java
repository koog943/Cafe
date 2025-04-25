package cafe.food.controller;

import cafe.food.domain.member.Address;
import cafe.food.domain.member.Member;
import cafe.food.request.MemberForm;
import cafe.food.response.ResMember;
import cafe.food.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/new")
    public String newMember(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String saveMember(@ModelAttribute(name = "memberForm") @Valid MemberForm memberForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() == true) {
            return "/members/createMemberForm";
        }

        Address address = new Address();
        address.setCity(memberForm.getCity());
        address.setZip(memberForm.getZip());
        address.setStreet(memberForm.getStreet());

        Member member = Member.builder()
                .name(memberForm.getName())
                .password(memberForm.getPassword())
                .email(memberForm.getEmail())
                .address(address)
                .build();

        Long saveId = memberService.save(member);
        redirectAttributes.addAttribute("saveId", saveId);
        return "redirect:/member/{saveId}";
    }

    @GetMapping("/members")
    public String members(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/members/memberList";
    }

    @GetMapping("/member/{id}")
    public String member(@PathVariable(name = "id") Long id, Model model) {
        Member findMember = memberService.findById(id);
        model.addAttribute("resMember", new ResMember(findMember));
        return "/members/member";
    }

}
