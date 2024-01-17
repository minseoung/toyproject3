package toy.toyproject3.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import toy.toyproject3.domain.entity.FileStore;
import toy.toyproject3.exception.AlreadyExistLoginIdException;
import toy.toyproject3.exception.LoginFailedException;
import toy.toyproject3.service.MemberService;
import toy.toyproject3.web.dto.LoginRequest;
import toy.toyproject3.web.dto.MemberAddRequest;

import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final FileStore fileStore;

    @Value("file.dir")
    private String fileDir;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("addRequest", new MemberAddRequest());
        return "member/addForm";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute(name = "addRequest") MemberAddRequest addRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/addForm";
        }
        try{
            memberService.join(addRequest);
        }catch (AlreadyExistLoginIdException e){
            bindingResult.reject("alreadyExist", new Object[]{}, null);
            return "member/addForm";
        }
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "member/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(name = "redirectURL", defaultValue = "/home/logined") String redirectURL) {
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

    @ResponseBody
    @GetMapping("/pfp/{dbFileName}")
    public Resource downloadPfp(@PathVariable(name = "dbFileName") String dbFileName) {
        log.info("사진 보여주기 실행");
        try {
            return new UrlResource("file:" + fileStore.getFullPath(dbFileName));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
