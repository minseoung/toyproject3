package toy.toyproject3.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.UploadFile;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardEditResponse {
    private Long boardId;
    private String writer;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String[] images;
    private List<MultipartFile> files;

    public BoardEditResponse(Board board) {
        this.boardId = board.getId();
        this.writer = board.getMember().getLoginid();
        this.title = board.getTitle();
        this.content = board.getContent();
        if (board.getImages() != null) {
            this.images = board.getImages().split(" ");
        }
    }
}
