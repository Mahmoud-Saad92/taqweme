package eg.bazinga.taqweme.domains;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity extends AuditEntity implements Serializable {

    public BaseEntity() {
    }

    public BaseEntity(String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate) {
        super(createdBy, createdDate, modifiedBy, modifiedDate);
    }

    public abstract Long getId();
}
