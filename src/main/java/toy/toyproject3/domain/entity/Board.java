package toy.toyproject3.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import toy.toyproject3.domain.entity.auditing.AuditingBy;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends AuditingBy {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
