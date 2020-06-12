package eg.bazinga.taqweme.services.user;

import eg.bazinga.taqweme.domains.SysUser;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;
import eg.bazinga.taqweme.repositories.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserRepository repository;

    @Autowired
    public SysUserServiceImpl(SysUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public SysUser createUser(SysUser user) {
        return repository.save(user);
    }

    @Override
    public Set<SysUser> getAllUsers() {
        return new HashSet<>(repository.findAll(Sort.by(Sort.Order.asc("id"))));
    }

    @Override
    public SysUser getUserById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    }

    @Override
    public SysUser updateUser(Long id, SysUser user) throws ResourceNotFoundException {
        SysUser oldUser = getUserById(id);

        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());

        return repository.save(oldUser);
    }

    @Override
    public void deleteUser(Long id) throws ResourceNotFoundException {
        repository.delete(getUserById(id));
    }
}
