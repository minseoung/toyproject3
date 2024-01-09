package toy.toyproject3.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembersRequest {
    private Integer page;
    private Integer size;
}
