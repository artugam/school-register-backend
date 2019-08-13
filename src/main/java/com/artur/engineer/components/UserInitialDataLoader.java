package com.artur.engineer.components;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.managers.UserManager;
import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.repositories.RoleRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
public class UserInitialDataLoader extends InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManager userManager;

    @Autowired
    private RoleRepository roleRepository;


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

        try {
            this.createAdminIfNotExist();
            this.createDefaultUserIfNotExist();
        } catch (ApiException exc) {
            //do Nothing
        }

    }

    @Transactional
    public void createDefaultUserIfNotExist() throws ApiException {
        if (null != userRepository.findByEmail("user@user.com")) {
            return;
        }
        Role userRole = roleRepository.findByName("ROLE_USER");
        User user = userManager.create("User", "User", "user@user.com", "user", Arrays.asList(userRole), true);
    }

    @Transactional
    public void createAdminIfNotExist() throws ApiException {
        if (null != userRepository.findByEmail("admin@admin.com")) {
            return;
        }
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User admin = userManager.create("Admin", "Admin", "admin@admin.com", "admin", Arrays.asList(adminRole, userRole), true);
    }
}