package toy.toyproject3.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import toy.toyproject3.domain.entity.auditing.AuditingDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uniqueConst", columnNames = {"loginid"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends AuditingDate {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private int age;
    private String loginid;
    private String pw;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    public Member(String name, int age, String loginid, String pw) {
        this.name = name;
        this.age = age;
        this.loginid = loginid;
        this.pw = pw;
    }
}
