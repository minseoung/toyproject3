package toy.toyproject3.domain.entity.auditing;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingBy extends AuditingDate{
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;
}
