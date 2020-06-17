package eg.bazinga.taqweme.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eg.bazinga.taqweme.domains.Role;
import eg.bazinga.taqweme.domains.SysUser;
import eg.bazinga.taqweme.enums.ERole;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SysUserDTO {

    private Long id;
    private String name;
    private String username;
    private String emailAddress;
    @JsonIgnore
    private String password;
    private String phoneNumber;
    private Boolean active;
    private Set<String> roles;

    public SysUserDTO(SysUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.emailAddress = user.getEmailAddress();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.active = user.getActive();
        this.roles =
                user
                        .getRoles()
                        .stream()
                        .filter(Objects::nonNull)
                        .sorted(Comparator.comparingLong(Role::getId))
                        .map(Role::getRoleName)
                        .map(ERole::getRole)
                        .collect(Collectors.toCollection(HashSet::new));
        System.out.println(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserDTO that = (SysUserDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "SysUserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
