package com.artur.engineer.controllers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.managers.UserManager;
import com.artur.engineer.entities.User;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Controller
@RequestMapping(path = "/api/auth")
public class AuthenticatorController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManager userManager;

    @PostMapping(path = "/login")
    public @ResponseBody
    User create(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        try {
            return userManager.create(firstName, lastName, email, password);
        } catch (ApiException exc)
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }

    }
}
