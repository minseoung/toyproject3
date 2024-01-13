package toy.toyproject3.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAddRequest {
    @NotBlank
    private String name;
    @NotNull
    @Min(value = 20)
    private Integer age;
    @NotBlank
    @Length(min = 8, max = 12)
    private String loginid;
    @NotBlank
    private String pw;
}
