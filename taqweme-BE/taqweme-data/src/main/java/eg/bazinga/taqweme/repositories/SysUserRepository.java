package eg.bazinga.taqweme.repositories;

import eg.bazinga.taqweme.domains.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
}
