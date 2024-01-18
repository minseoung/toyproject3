package toy.toyproject3.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;
import toy.toyproject3.domain.entity.auditing.AuditingBy;

import java.util.ArrayList;
import java.util.List;

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
    private String images;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    public Board(String title, String content, Member member, List<UploadFile> images) {
        this.title = title;
        this.content = content;
        addMember(member);
        StringBuffer stringBuffer = new StringBuffer();
        if (!images.isEmpty()) {
            for (UploadFile image : images) {
                if (image != null) {
                    stringBuffer.append(image.getDbFileName() + " ");
                }
            }
            this.images = stringBuffer.toString();
        }
    }

    private void addMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    public void edit(String title, String content, List<UploadFile> uploadFiles) {
        this.title = title;
        this.content = content;
        StringBuffer stringBuffer = new StringBuffer();
        if (!uploadFiles.isEmpty()) {
            for (UploadFile image : uploadFiles) {
                if (image != null) {
                    stringBuffer.append(image.getDbFileName() + " ");
                }
            }
            this.images = stringBuffer.toString();
        }
    }
}
