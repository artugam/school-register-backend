package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.Role;
import com.artur.engineer.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("RoleReader")
public class RoleReader {

    @Autowired
    private RoleRepository roleRepository;

    public Collection<Role> getUserRoleAsCollection() {
        Role userRole = roleRepository.findByName("ROLE_USER");

        return Arrays.asList(userRole);
    }

    public List<Role> getUserRoles(Long roleId)
    {
        return this.roleRepository.findAllByNameIn(this.getUserRolesNames(roleId));
    }


    public Iterable getUserRolesNames(Long roleId) {

        Role userRole = roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role not found with id : " + roleId)
        );

        return this.getUserRolesNames(userRole);
    }

    public Iterable getUserRolesNames(Role role) {
        return this.getUserRolesNames(role.getName());
    }

    public Iterable getUserRolesNames(String roleName) {
        switch (roleName) {
            case Role.ROLE_ADMIN:
                return this.getAdminRoles();
            case Role.ROLE_TEACHER:
                return this.getTeacherRoles();
            case Role.ROLE_SUPER_USER:
                return this.getSuperUserRoles();

        }
        return this.getUserRoles();
    }

    public Iterable getAdminRoles() {
        return Arrays.asList(
                Role.ROLE_ADMIN,
                Role.ROLE_TEACHER,
                Role.ROLE_SUPER_USER,
                Role.ROLE_USER
        );
    }

    public Iterable getSuperUserRoles() {
        return Arrays.asList(
                Role.ROLE_SUPER_USER,
                Role.ROLE_USER
        );
    }

    public Iterable getTeacherRoles() {
        return Arrays.asList(
                Role.ROLE_TEACHER,
                Role.ROLE_SUPER_USER,
                Role.ROLE_USER
        );
    }

    public Iterable getUserRoles() {
        return Arrays.asList(
                Role.ROLE_USER
        );
    }
}
