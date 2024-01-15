package toy.toyproject3.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.service.BoardService;
import toy.toyproject3.web.argumentResolver.Login;
import toy.toyproject3.web.dto.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/boards")
    public String boards(@ModelAttribute(name = "boardsRequest")BoardsRequest boardsRequest,
                         @PageableDefault(page = 0, size = 10, sort = "lastModifiedDate", direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {
        Page<BoardsResponse> boards = boardService.findBoards(boardsRequest, pageable);

        int nowPage = boards.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, boards.getTotalPages());

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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
    public String board(@PathVariable(name = "id") Long id, Model model, @Login Long loginMemberId) {
        BoardResponse response = boardService.findBord(id, loginMemberId);
        model.addAttribute("response", response);
        return "board/board";
    }

    @GetMapping("/boards/{id}/edit")
    public String editForm(@PathVariable(name = "id") Long id, Model model) {
        BoardEditResponse boardEditResponse = boardService.editForm(id);
        model.addAttribute("response", boardEditResponse);
        return "board/editForm";
    }

    @PostMapping("/boards/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, @Validated @ModelAttribute(name = "response") BoardEditResponse response, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "board/editForm";
        }
        boardService.edit(id, response);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/boards/{id}";
    }

    @PostMapping("/boards/{id}/delete")
    public String delete(@PathVariable(name = "id") Long id) {
        boardService.delete(id);
        return "redirect:/boards";
    }
}
