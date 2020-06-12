package eg.bazinga.taqweme.services.user;

import eg.bazinga.taqweme.domains.SysUser;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;

import java.util.Set;

public interface SysUserService {

    SysUser createUser(SysUser user);

    Set<SysUser> getAllUsers();

    SysUser getUserById(Long id) throws ResourceNotFoundException;

    SysUser updateUser(Long id, SysUser user) throws ResourceNotFoundException;

    void deleteUser(Long id) throws ResourceNotFoundException;
}
