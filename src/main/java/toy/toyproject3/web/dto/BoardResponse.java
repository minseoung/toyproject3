package toy.toyproject3.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import toy.toyproject3.domain.entity.Board;

@Data
@NoArgsConstructor
public class BoardResponse {
    private Long boardMemberId;
    private Long loginMemberId;
    private Long boardId;
    private String title;
    private String content;
    private String writer;

    public BoardResponse(Board board, Long loginMemberId) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getMember().getLoginid();
        this.boardMemberId = board.getMember().getId();
        this.loginMemberId = loginMemberId;
    }
}
