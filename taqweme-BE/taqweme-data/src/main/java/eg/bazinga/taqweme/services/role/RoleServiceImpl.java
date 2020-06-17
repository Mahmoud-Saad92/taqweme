package eg.bazinga.taqweme.services.role;

import eg.bazinga.taqweme.domains.Role;
import eg.bazinga.taqweme.enums.ERole;
import eg.bazinga.taqweme.exceptions.ResourceCannotCreatedException;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;
import eg.bazinga.taqweme.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getAllRoles() {
        return new ArrayList<>(repository.findAll(Sort.by(Sort.Order.asc("id"))));
    }

    @Override
    public Role createRole(Role role) throws ResourceCannotCreatedException {
        Role savedRole;

        try {
            savedRole = repository.save(role);
        } catch (Exception e) {
            throw  new ResourceCannotCreatedException("Role Cannot Created!!");
        }

        return savedRole;
    }

    @Override
    public Role getRoleByName(ERole name) throws ResourceNotFoundException {
        return repository.findByRoleName(name).orElseThrow(() -> new ResourceNotFoundException("Role not found for this name :: " + name));
    }
}
