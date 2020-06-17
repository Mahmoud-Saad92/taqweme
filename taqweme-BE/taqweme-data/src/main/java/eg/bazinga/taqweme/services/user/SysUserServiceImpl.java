package eg.bazinga.taqweme.services.user;

import eg.bazinga.taqweme.domains.SysUser;
import eg.bazinga.taqweme.dtos.SysUserDTO;
import eg.bazinga.taqweme.exceptions.ResourceCannotCreatedException;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;
import eg.bazinga.taqweme.repositories.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserRepository repository;

    @Autowired
    public SysUserServiceImpl(SysUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public SysUser createUser(SysUser user) throws ResourceCannotCreatedException {
        SysUser sysUser;

        try {
            sysUser = repository.save(user);
        } catch (Exception e) {
            throw new ResourceCannotCreatedException("ERROR: user creation failed!!");
        }

        return sysUser;
    }

    @Override
    public Set<SysUserDTO> getAllUsers() {
        return repository
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingLong(SysUser::getId))
                .map(SysUserDTO::new)
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public SysUser getUserById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    }

    @Override
    public SysUser updateUser(Long id, SysUser user) throws ResourceNotFoundException {
        SysUser oldUser = getUserById(id);

        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmailAddress(user.getEmailAddress());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setActive(user.getActive());
        oldUser.setRoles(user.getRoles());

        return repository.save(oldUser);
    }

    @Override
    public void deleteUser(Long id) throws ResourceNotFoundException {
        repository.delete(getUserById(id));
    }
}
