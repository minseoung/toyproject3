package toy.toyproject3.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardAddRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

}
