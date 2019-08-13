package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.Role;
import com.artur.engineer.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("RoleReader")
public class RoleReader {

    @Autowired
    private RoleRepository roleRepository;

    public Collection getUserRoleAsCollection() {
        Role userRole = roleRepository.findByName("ROLE_USER");

        return Arrays.asList(userRole);
    }
}
