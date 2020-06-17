package eg.bazinga.taqweme.services.role;

import eg.bazinga.taqweme.domains.Role;
import eg.bazinga.taqweme.enums.ERole;
import eg.bazinga.taqweme.exceptions.ResourceCannotCreatedException;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role createRole(Role role) throws ResourceCannotCreatedException;

    Role getRoleByName(ERole name) throws ResourceNotFoundException;
}
