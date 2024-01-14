package toy.toyproject3.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.service.BoardService;
import toy.toyproject3.web.argumentResolver.Login;
import toy.toyproject3.web.dto.BoardAddRequest;
import toy.toyproject3.web.dto.BoardResponse;
import toy.toyproject3.web.dto.BoardsRequest;
import toy.toyproject3.web.dto.BoardsResponse;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/boards")
    public String boards(@ModelAttribute(name = "boardsRequest")BoardsRequest boardsRequest, Model model) {
        List<BoardsResponse> boards = boardService.findBoards(boardsRequest);
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    @GetMapping("/boards/add")
    public String addForm(Model model) {
        model.addAttribute("addRequest", new BoardAddRequest());
        return "board/addForm";
    }

    @PostMapping("/boards/add")
    public String add(@Validated @ModelAttribute(name = "addRequest") BoardAddRequest addRequest, BindingResult bindingResult,
                      @Login Long loginMemberId) {
        if (bindingResult.hasErrors()) {
            return "board/addForm";
        }
        boardService.post(addRequest, loginMemberId);
        return "redirect:/boards";
    }

    @GetMapping("/boards/{id}")
    public String edit(@PathVariable(name = "id") Long id, Model model, @Login Long loginMemberId) {
        BoardResponse response = boardService.findBord(id, loginMemberId);
        model.addAttribute("response", response);
        return "board/board";
    }
}
