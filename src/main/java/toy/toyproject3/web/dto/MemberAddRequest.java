package toy.toyproject3.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAddRequest {
    private String name;
    private Integer age;
    private String loginid;
    private String pw;
}
