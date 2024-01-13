package toy.toyproject3.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import toy.toyproject3.domain.entity.Board;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardsResponse {
    private String title;
    private String writer;
    private LocalDateTime writtenDate;
    private Long boardId;
    private Long memberId;

    public BoardsResponse(Board board) {
        this.title = board.getTitle();
        this.writer = board.getMember().getLoginid();
        this.writtenDate = board.getLastModifiedDate();
        this.boardId = board.getId();
        this.memberId = board.getMember().getId();
    }
}
