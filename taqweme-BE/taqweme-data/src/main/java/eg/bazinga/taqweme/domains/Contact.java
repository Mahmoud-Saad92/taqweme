package eg.bazinga.taqweme.domains;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "CONTACT")
public class Contact extends BaseEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "CONTACT_SEQ_GENERATOR", sequenceName = "CONTACT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_SEQ_GENERATOR")
    private Long id;

    @NotEmpty(message = "Name is mandatory")
    @Column(name = "NAME", nullable = false)
    private String name;

    public Contact() {
    }

    @Builder
    public Contact(Long id, String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate,
                   @NotEmpty(message = "Name is mandatory") String name) {
        super(createdBy, createdDate, modifiedBy, modifiedDate);
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return getId().equals(contact.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
