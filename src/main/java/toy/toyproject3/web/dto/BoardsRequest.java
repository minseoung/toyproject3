package toy.toyproject3.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardsRequest {
    private String title;
    private String writer;

}
