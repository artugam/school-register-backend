package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.readers.RoleReader;
import com.artur.engineer.entities.User;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("AuthenticationManager")
public class AuthenticationManager {

    @Autowired
    private UserRepository userRepository;

//    public User login(String email, String password) throws ApiException {
//
////        this.validateUser(user);
//
////        return userRepository.save(user);
//    }

}
