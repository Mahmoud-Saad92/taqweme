package eg.bazinga.taqweme.services.user;

import eg.bazinga.taqweme.domains.SysUser;
import eg.bazinga.taqweme.dtos.SysUserDTO;
import eg.bazinga.taqweme.exceptions.ResourceCannotCreatedException;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;

import java.util.Set;

public interface SysUserService {

    SysUser createUser(SysUser user) throws ResourceCannotCreatedException;

    Set<SysUserDTO> getAllUsers();

    SysUser getUserById(Long id) throws ResourceNotFoundException;

    SysUser updateUser(Long id, SysUser user) throws ResourceNotFoundException;

    void deleteUser(Long id) throws ResourceNotFoundException;
}
