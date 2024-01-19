package toy.toyproject3.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.toyproject3.domain.entity.auditing.AuditingDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends AuditingDate {
    @Id @GeneratedValue
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(String content, Board board, Member member) {
        this.content = content;
        addBoard(board);
        addMember(member);
    }

    public void addBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }
    public void addMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }
}
