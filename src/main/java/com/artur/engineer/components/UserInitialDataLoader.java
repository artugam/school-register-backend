package com.artur.engineer.components;

import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.repositories.RoleRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
public class UserInitialDataLoader extends InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Role createRoleIfNotFound(
            String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
        return role;
    }

    @Override
    @Transactional
    public void run() {
        this.createRoleIfNotFound("ROLE_ADMIN");
        this.createRoleIfNotFound("ROLE_USER");

        this.createAdminIfNotExist();
        this.createDefaultUserInfNotExist();
    }

    @Transactional
    public void createDefaultUserInfNotExist() {
        if (null != userRepository.findByEmail("user@user.com")) {
            return;
        }
        Role userRole = roleRepository.findByName("ROLE_USER");
        User user = new User();
        user.setFirstName("User");
        user.setLastName("User");
        user.setPassword(passwordEncoder.encode("user"));
        user.setEmail("user@user.com");
        user.setRoles(Arrays.asList(userRole));
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Transactional
    public void createAdminIfNotExist() {
        if (null != userRepository.findByEmail("admin@admin.com")) {
            return;
        }
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@admin.com");
        admin.setRoles(Arrays.asList(adminRole, userRole));
        admin.setEnabled(true);
        userRepository.save(admin);

    }
}