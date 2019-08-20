package com.artur.engineer.controllers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.managers.UserManager;
import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.User;
import com.artur.engineer.repositories.UserRepository;
import com.artur.engineer.security.CurrentUser;
import com.artur.engineer.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManager userManager;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.Normal.class})
    public
    Iterable<User> getAll() {
        return this.userRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(path = "/me")
    @ResponseStatus(HttpStatus.OK)
    public
    Optional<User> me(@CurrentUser UserPrincipal currentUser) {
        return userRepository.findById(currentUser.getId());
    }

    @PostMapping(path = "")
    public
    User create(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {


        try {
            return userManager.create(firstName, lastName, email, password);
        } catch (ApiException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }

    }
}