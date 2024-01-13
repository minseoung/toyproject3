package toy.toyproject3.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toy.toyproject3.service.MemberService;
import toy.toyproject3.web.argumentResolver.Login;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/home/logined")
    public String home_logined(Model model, @Login Long loginMemberId) {
        String loginid = memberService.findMember(loginMemberId).getLoginid();
        model.addAttribute("loginid", loginid);
        return "home_logined";
    }



//    @GetMapping("/loginedHome")
//    public String loginedHome() {
//        return
//    }
}
