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
@Component("UserManager")
public class UserManager {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleReader roleReader;

    @Autowired
    private UserRepository userRepository;

    public User create(String firstName, String lastName, String email, String password, Collection roles, boolean enabled) throws ApiException {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        user.setEnabled(enabled);

        this.validateUser(user);

        return userRepository.save(user);
    }

    public User create(String firstName, String lastName, String email, String password) throws ApiException {
        return this.create(firstName, lastName, email, password, roleReader.getUserRoleAsCollection(), true);
    }

    public void validateUser(User user) throws ApiException {
        if (null != userRepository.findByEmail(user.getEmail())) {
            throw new ApiException("User with provided email already exist");
        }
    }
}
