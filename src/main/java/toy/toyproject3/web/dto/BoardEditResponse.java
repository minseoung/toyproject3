package toy.toyproject3.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.toyproject3.domain.entity.Board;

@Data
@NoArgsConstructor
public class BoardEditResponse {
    private Long boardId;
    private String writer;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public BoardEditResponse(Board board) {
        this.boardId = board.getId();
        this.writer = board.getMember().getLoginid();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
