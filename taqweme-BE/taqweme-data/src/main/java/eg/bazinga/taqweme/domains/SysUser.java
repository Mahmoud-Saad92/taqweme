package eg.bazinga.taqweme.domains;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SYS_USER")
public class SysUser implements Serializable {

    @Id
    @SequenceGenerator(name = "SYS_USER_SEQ_GENERATOR", sequenceName = "SYS_USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_USER_SEQ_GENERATOR")
    private long id;

    @NotEmpty(message = "First name is mandatory")
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name is mandatory")
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Email
    @NotEmpty(message = "Email is mandatory")
    @Column(name = "EMAIL", nullable = false)
    private String email;

    public SysUser() {
    }

    public SysUser(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return getId() == sysUser.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}