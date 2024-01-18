package toy.toyproject3.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import toy.toyproject3.domain.entity.Member;
import toy.toyproject3.domain.entity.UploadFile;

@Data
@NoArgsConstructor
public class MemberEditResponse {
    @NotBlank
    private String loginid;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
    @NotNull
    private int age;
    private MultipartFile file;
    private UploadFile pfp;

    public MemberEditResponse(Member member) {
        loginid = member.getLoginid();
        pw = member.getPw();
        name = member.getName();
        age = member.getAge();
        pfp = member.getPfp();
    }
}
