package toy.toyproject3.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentAddRequest {
    private String content;
}
