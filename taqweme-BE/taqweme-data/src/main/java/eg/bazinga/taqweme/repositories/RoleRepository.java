package eg.bazinga.taqweme.repositories;

import eg.bazinga.taqweme.domains.Role;
import eg.bazinga.taqweme.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(ERole roleName);

}
