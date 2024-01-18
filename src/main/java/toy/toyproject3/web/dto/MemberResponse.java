package toy.toyproject3.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.Member;
import toy.toyproject3.domain.entity.UploadFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MemberResponse {
    private Long id;
    private String loginid;
    private String name;
    private int age;
    private Page<BoardDto> boards;
    private UploadFile pfp;
    private int startPage;
    private int endPage;

    public MemberResponse(Member member, Pageable pageable) {
        id = member.getId();
        loginid = member.getLoginid();
        name = member.getName();
        age = member.getAge();
        pfp = member.getPfp();

        List<Board> memberBoards = member.getBoards();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), memberBoards.size());
        Page<Board> boardPage = new PageImpl<>(memberBoards.subList(start, end), pageable, memberBoards.size());
        boards = boardPage.map(board -> new BoardDto(board));

        int blockLimit = 3;
        startPage = (((int) (Math.ceil((double) (pageable.getPageNumber() + 1) / blockLimit))) - 1) * blockLimit + 1;
        endPage = startPage + blockLimit - 1;
        if (endPage > boards.getTotalPages()) {
            endPage = boards.getTotalPages();
        }
        if (endPage == 0) {
            endPage = 1;
        }
    }

    @Data
    static class BoardDto {
        private Long boardId;
        private String title;
        private LocalDateTime writtenDate;

        public BoardDto(Board board) {
            boardId = board.getId();
            title = board.getTitle();
            writtenDate = board.getLastModifiedDate();
        }
    }

}
