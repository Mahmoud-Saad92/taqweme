package eg.bazinga.taqweme.bootstrap;

import eg.bazinga.taqweme.domains.Contact;
import eg.bazinga.taqweme.domains.Role;
import eg.bazinga.taqweme.domains.SysUser;
import eg.bazinga.taqweme.enums.ERole;
import eg.bazinga.taqweme.exceptions.ResourceCannotCreatedException;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;
import eg.bazinga.taqweme.services.contact.ContactService;
import eg.bazinga.taqweme.services.role.RoleService;
import eg.bazinga.taqweme.services.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private RoleService roleServiceImpl;

    private SysUserService sysUserServiceImpl;

    private ContactService contactServiceImpl;

    @Autowired
    public DataLoader(RoleService roleServiceImpl, SysUserService sysUserServiceImpl, ContactService contactServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
        this.sysUserServiceImpl = sysUserServiceImpl;
        this.contactServiceImpl = contactServiceImpl;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (roleServiceImpl.getAllRoles().isEmpty()) {
            initRoles();
        }

        if (sysUserServiceImpl.getAllUsers().isEmpty()) {
            initUsers();
        }

        if (contactServiceImpl.getAllContacts().isEmpty()) {
            initContacts();
        }

        System.out.println("Data Loaded Successfully!!");
    }

    public void initRoles() throws ResourceCannotCreatedException {
        Role admin =
                Role
                        .builder()
                        .roleName(ERole.ROLE_ADMIN)
                        .createdBy("SYSTEM")
                        .createdDate(LocalDateTime.now())
                        .modifiedBy("SYSTEM")
                        .modifiedDate(LocalDateTime.now())
                        .build();

        roleServiceImpl.createRole(admin);

        Role user =
                Role
                        .builder()
                        .roleName(ERole.ROLE_USER)
                        .createdBy("SYSTEM")
                        .createdDate(LocalDateTime.now())
                        .modifiedBy("SYSTEM")
                        .modifiedDate(LocalDateTime.now())
                        .build();

        roleServiceImpl.createRole(user);
    }

    public void initUsers() throws ResourceCannotCreatedException, ResourceNotFoundException {

        Role admin = roleServiceImpl.getRoleByName(ERole.ROLE_ADMIN);
        Role user = roleServiceImpl.getRoleByName(ERole.ROLE_USER);

        SysUser sysUser =
                SysUser
                        .builder()
                        .name("ADMIN")
                        .username("admin")
                        .emailAddress("admin.dummy@eg.bazinga.com")
                        .phoneNumber("01096942732")
                        .password("$2a$04$OB8ZRD58f3HFW3M.U3WqyeQWgsGBm6lwXRHdwH.F.YUckJYSqSFSy")
                        .createdBy("SYSTEM")
                        .createdDate(LocalDateTime.now())
                        .modifiedBy("SYSTEM")
                        .modifiedDate(LocalDateTime.now())
                        .build();

        sysUser.addRole(admin);
        sysUser.addRole(user);

        sysUserServiceImpl.createUser(sysUser);
    }

    public void initContacts() throws ResourceCannotCreatedException {
        Contact contact =
                Contact
                        .builder()
                        .name("ADMIN")
                        .createdBy("SYSTEM")
                        .createdDate(LocalDateTime.now())
                        .modifiedBy("SYSTEM")
                        .modifiedDate(LocalDateTime.now())
                        .build();

        contactServiceImpl.createContact(contact);
    }
}
