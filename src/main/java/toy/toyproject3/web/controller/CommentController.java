package toy.toyproject3.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toy.toyproject3.domain.repository.BoardRepository;
import toy.toyproject3.service.CommentService;
import toy.toyproject3.web.argumentResolver.Login;
import toy.toyproject3.web.dto.CommentAddRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

//    @PostMapping("/comments/add")
//    public String add(@ModelAttribute(name = "request") CommentAddRequest request, @Login Long loginMemberId) {
//        commentService.add(request);
//    }
}
