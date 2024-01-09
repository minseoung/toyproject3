package toy.toyproject3.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toy.toyproject3.exception.LoginFailedException;
import toy.toyproject3.service.MemberService;
import toy.toyproject3.web.dto.LoginRequest;
import toy.toyproject3.web.dto.MemberAddRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm() {
        return "member/addForm";
    }

    @PostMapping("/add")
    public String add(@Validated MemberAddRequest addRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/addForm";
        }
        memberService.join(addRequest);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "member/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(name = "redirectURL", defaultValue = "/board/boards") String redirectURL) {
        if (bindingResult.hasErrors()) {
            return "member/loginForm";
        }
        Long loginMemberId;
        try {
             loginMemberId = memberService.login(loginRequest);
        } catch (LoginFailedException e) {
            bindingResult.reject("loginFailed", new Object[]{}, null);
            return "member/loginForm";
        }
        request.getSession().setAttribute("loginMemberId", loginMemberId);
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/home";
    }
}
