package com.artur.engineer.controllers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.managers.UserManager;
import com.artur.engineer.engine.views.user.UserViews;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.repositories.UserRepository;
import com.artur.engineer.security.CurrentUser;
import com.artur.engineer.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import groovy.util.logging.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


import org.apache.logging.log4j.LogManager;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Controller
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManager userManager;


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(path = "")
    @JsonView(UserViews.Normal.class)
    public @ResponseBody
    Iterable<User> getAll() throws JsonProcessingException {

        Iterable<User> list = this.userRepository.findAll();
        return list;
//        ObjectMapper mapper = new ObjectMapper();
//
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        String view = mapper.writerWithView(UserViews.Normal.class).writeValueAsString(list);
//
//        return new ApiResponse(true, view);

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(path = "/me")
    public @ResponseBody
    Optional<User> me(@CurrentUser UserPrincipal currentUser) {

        return userRepository.findById(currentUser.getId());
    }


    @PostMapping(path = "")
    public @ResponseBody
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