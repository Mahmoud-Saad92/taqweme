package eg.bazinga.taqweme.controllers;

import eg.bazinga.taqweme.domains.SysUser;
import eg.bazinga.taqweme.dtos.SysUserDTO;
import eg.bazinga.taqweme.exceptions.ResourceCannotCreatedException;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;
import eg.bazinga.taqweme.services.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class SysUserController {

    private SysUserService sysUserServiceImpl;

    @Autowired
    public SysUserController(SysUserService sysUserServiceImpl) {
        this.sysUserServiceImpl = sysUserServiceImpl;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SysUser> createUser(@Valid @RequestBody SysUser user) throws ResourceCannotCreatedException {
        return ResponseEntity.ok().body(sysUserServiceImpl.createUser(user));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<SysUserDTO>> getAllUsers() {
        return ResponseEntity.ok().body(sysUserServiceImpl.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SysUser> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sysUserServiceImpl.getUserById(id));
    }
}
