package com.artur.engineer.controllers;

import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.Role;
import com.artur.engineer.repositories.RoleRepository;
import com.artur.engineer.security.CurrentUser;
import com.artur.engineer.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/api/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;


    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.class})
    public
    Iterable<Role> getAll(
            @CurrentUser UserPrincipal currentUser
    ) {
        if(!currentUser.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN))) {
            return this.roleRepository.findAllByNameIn(Arrays.asList(Role.ROLE_TEACHER, Role.ROLE_SUPER_USER, Role.ROLE_USER));
        }
        return this.roleRepository.findAll();
    }


}
