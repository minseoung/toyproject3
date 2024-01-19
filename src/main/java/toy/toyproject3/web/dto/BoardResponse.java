package toy.toyproject3.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.Comment;
import toy.toyproject3.domain.entity.Member;
import toy.toyproject3.domain.entity.UploadFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BoardResponse {
    private Long boardMemberId;
    private Long loginMemberId;
    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private String[] images;
    private UploadFile pfp;
    private List<CommentDTO> comments;


    public BoardResponse(Board board, Member member) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getMember().getLoginid();
        this.boardMemberId = board.getMember().getId();
        this.loginMemberId = member.getId();
        this.pfp = member.getPfp();
        if (board.getImages() != null) {
            this.images = board.getImages().split(" ");
        }
        List<Comment> comments = board.getComments();
        List<CommentDTO> collect = comments.stream().map(comment -> new CommentDTO(comment)).collect(Collectors.toList());
        this.comments = collect;
    }

    @Data
    @NoArgsConstructor
    static class CommentDTO {
        private String content;
        private LocalDateTime writtenDate;
        private UploadFile pfp;
        private String writer;

        public CommentDTO(Comment comment) {
            this.content = comment.getContent();
            this.writtenDate = comment.getLastModifiedDate();
            this.pfp = comment.getMember().getPfp();
            this.writer = comment.getMember().getLoginid();
        }
    }

}
