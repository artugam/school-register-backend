package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.readers.RoleReader;
import com.artur.engineer.engine.readers.UserReader;
import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.user.UserCreate;
import com.artur.engineer.payload.user.UserCreateWithPassword;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
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

    @Autowired
    private UserReader userReader;

    public User createOrUpdate(User user, String firstName, String lastName, String email, String password, Collection<Role> roles, boolean enabled) throws ApiException {

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        if (!password.equals("")) {
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setRoles(roles);
        user.setEnabled(enabled);

        return userRepository.save(user);
    }

    public User create(UserCreateWithPassword userCreate) throws ApiException {
        return this.createOrUpdate(new User(), userCreate.getFirstName(), userCreate.getLastName(), userCreate.getEmail(), userCreate.getPassword(), roleReader.getUserRoles(userCreate.getRole()), true);
    }

    public User edit(Long id, UserCreate userCreate) throws ApiException {
        User user = userReader.get(id);
        return this.createOrUpdate(user, userCreate.getFirstName(), userCreate.getLastName(), userCreate.getEmail(), "", roleReader.getUserRoles(userCreate.getRole()), true);
    }

    @Transactional
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public User block(Long id) {
        return this.setUserStatus(id, false);
    }

    public User unblock(Long id) {
        return this.setUserStatus(id, true);
    }

    public User setUserStatus(Long id, boolean status) {
        User user = userReader.get(id);
        user.setEnabled(status);
        userRepository.save(user);

        return user;
    }
}
