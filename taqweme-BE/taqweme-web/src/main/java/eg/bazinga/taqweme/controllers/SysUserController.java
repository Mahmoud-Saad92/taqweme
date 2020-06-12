package eg.bazinga.taqweme.controllers;

import eg.bazinga.taqweme.domains.SysUser;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;
import eg.bazinga.taqweme.services.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SysUser> createUser(@Valid @RequestBody SysUser user) {
        return ResponseEntity.ok().body(sysUserServiceImpl.createUser(user));
    }

    @GetMapping("")
    public ResponseEntity<Set<SysUser>> getAllUsers() {
        return ResponseEntity.ok().body(sysUserServiceImpl.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SysUser> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sysUserServiceImpl.getUserById(id));
    }
}
