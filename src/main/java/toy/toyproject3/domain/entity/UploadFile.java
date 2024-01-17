package toy.toyproject3.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadFile {
    private String userFileName;
    private String dbFileName;

    public UploadFile(String userFileName, String dbFileName) {
        this.userFileName = userFileName;
        this.dbFileName = dbFileName;
    }
}
