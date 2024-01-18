package toy.toyproject3.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardAddRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private List<MultipartFile> images;
}
