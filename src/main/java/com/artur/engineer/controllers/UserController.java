package com.artur.engineer.controllers;

import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.repositories.RoleRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Controller
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "")
    public @ResponseBody
    Iterable<User> getAll() {
        return this.userRepository.findAll();
    }

    @PostMapping(path = "")
    public @ResponseBody
    User create(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
            ) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(true);


        return this.userRepository.save(user);
    }


}