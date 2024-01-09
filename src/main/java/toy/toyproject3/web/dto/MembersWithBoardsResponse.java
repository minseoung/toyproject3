package toy.toyproject3.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembersWithBoardsResponse {

    private Long id;
    private String name;
    private int age;
    private List<BoardResponse> boards = new ArrayList<>();

    public MembersWithBoardsResponse(Member member) {
        id = member.getId();
        name = member.getName();
        age = member.getAge();
        boards = member.getBoards().stream()
                .map(board -> new BoardResponse(board)).collect(Collectors.toList());
    }

    @Data
    static class BoardResponse {
        private Long id;
        private String title;
        private String content;

        public BoardResponse(Board board) {
            id = board.getId();
            title = board.getTitle();
            content = board.getTitle();
        }
    }

}
