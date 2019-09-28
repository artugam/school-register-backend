package com.artur.engineer.controllers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.managers.UserManager;
import com.artur.engineer.engine.readers.UserReader;
import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.user.UserCreate;
import com.artur.engineer.payload.user.UserCreateWithPassword;
import com.artur.engineer.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserReader userReader;

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

//    @PostMapping(path = "")
//    public
//    User create(
//            @RequestParam("firstName") String firstName,
//            @RequestParam("lastName") String lastName,
//            @RequestParam("email") String email,
//            @RequestParam("password") String password
//    ) {
//        try {
//            return userManager.create(firstName, lastName, email, password);
//        } catch (ApiException exc) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
//        }
//
//    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.Normal.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User createUser(@Valid @RequestBody UserCreateWithPassword userCreateRequest) throws ApiException {

        return userManager.create(userCreateRequest);
    }

    @PatchMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.Normal.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User editUser(@PathVariable(value="userId") Long id, @Valid @RequestBody UserCreate userCreateRequest) throws ApiException {
        return userManager.edit(id, userCreateRequest);
    }

    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.Normal.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteUser(@PathVariable(value="userId") Long id) throws ApiException {
        userManager.remove(id);
        return true;
    }

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.Normal.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUser(@PathVariable(value="userId") Long id) throws ApiException {
        return userReader.get(id);
    }

    @PostMapping(path = "/{userId}/block")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.Normal.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User blockUser(@PathVariable(value="userId") Long id) throws ApiException {
        return userManager.block(id);
    }

    @PostMapping(path = "/{userId}/unblock")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.Normal.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User unblockUser(@PathVariable(value="userId") Long id) throws ApiException {
        return userManager.unblock(id);
    }


}