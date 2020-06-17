package eg.bazinga.taqweme.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "SYS_USER")
public class SysUser extends Person implements Serializable {

    @Id
    @SequenceGenerator(name = "SYS_USER_SEQ_GENERATOR", sequenceName = "SYS_USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_USER_SEQ_GENERATOR")
    private Long id;

    @NotEmpty(message = "Username is mandatory")
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @NotEmpty(message = "Password is mandatory")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @JsonIgnoreProperties("sysUserSet")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    public SysUser() {
    }

    @Builder
    public SysUser(Long id, String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate,
                   @NotEmpty(message = "Name is mandatory") String name,
                   @Email @Size(max = 50) @NotEmpty(message = "Email is mandatory") String emailAddress,
                   String phoneNumber, Boolean active,
                   @NotEmpty(message = "Username is mandatory") String username,
                   @NotEmpty(message = "Password is mandatory") String password,
                   Set<Role> roles) {
        super(createdBy, createdDate, modifiedBy, modifiedDate, name, emailAddress, phoneNumber, active);
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        if (roles == null) {
            roles = new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // helper methods
    public void addRole(Role role) {
        getRoles().add(role);
        role.getSysUserSet().add(this);
    }

    public void removeRole(Role role) {
        getRoles().remove(role);
        role.getSysUserSet().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return getId().equals(sysUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "SysUserDTO{" +
                "id=" + id +
                ", name='" + this.getName() + '\'' +
                ", username='" + username + '\'' +
                ", emailAddress='" + this.getEmailAddress() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", active=" + this.getActive() +
                ", roles=" + roles +
                ", createdBy=" + this.getCreatedBy() +
                ", createdDate=" + this.getCreatedDate() +
                ", ModifiedBy=" + this.getModifiedBy() +
                ", ModifiedDate=" + this.getModifiedDate() +
                '}';
    }
}