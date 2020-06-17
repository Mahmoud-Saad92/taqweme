package eg.bazinga.taqweme.domains;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Person extends BaseEntity implements Serializable {

    @NotEmpty(message = "Name is mandatory")
    @Column(name = "NAME", nullable = false)
    private String name;

    @Email
    @Size(max = 50)
    @NotEmpty(message = "Email is mandatory")
    @Column(name = "EMAIL_ADDRESS", nullable = false)
    private String emailAddress;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "IS_ACTIVE")
    private Boolean active;

    public Person() {
    }

    public Person(String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate,
                  @NotEmpty(message = "Name is mandatory") String name,
                  @Email @Size(max = 50) @NotEmpty(message = "Email is mandatory") String emailAddress,
                  String phoneNumber, Boolean active) {
        super(createdBy, createdDate, modifiedBy, modifiedDate);
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActive() {
        if (active == null) {
            this.active = true;
        }
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
