package eg.bazinga.taqweme.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eg.bazinga.taqweme.converters.ERoleJpaConverter;
import eg.bazinga.taqweme.enums.ERole;
import lombok.Builder;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "ROLE_SEQ_GENERATOR", sequenceName = "ROLE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ_GENERATOR")
    private Long id;

    @NaturalId
    @Column(name = "ROLE_NAME", length = 20, nullable = false)
    @Convert(converter = ERoleJpaConverter.class)
    private ERole roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<SysUser> sysUserSet;

    public Role() {
    }

    @Builder
    public Role(String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate,
                ERole roleName) {
        super(createdBy, createdDate, modifiedBy, modifiedDate);
        this.roleName = roleName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public ERole getRoleName() {
        return roleName;
    }

    public void setRoleName(ERole roleName) {
        this.roleName = roleName;
    }

    public Set<SysUser> getSysUserSet() {
        if (sysUserSet == null) {
            sysUserSet = new HashSet<>();
        }
        return sysUserSet;
    }

    public void setSysUserSet(Set<SysUser> sysUserSet) {
        this.sysUserSet = sysUserSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return getRoleName() == role.getRoleName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleName());
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName=" + roleName +
                '}';
    }
}
