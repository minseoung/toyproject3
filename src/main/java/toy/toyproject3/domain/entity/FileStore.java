package toy.toyproject3.domain.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public UploadFile saveFile(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();

        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1); //확장자
        String dbFileName = uuid + "." + ext;
        try {
            multipartFile.transferTo(new File(getFullPath(dbFileName)));
        } catch (Exception e) {
            log.error("IOEXCEPTION {}", e.getMessage());
        }
        return new UploadFile(originalFilename, dbFileName);

    }
}
