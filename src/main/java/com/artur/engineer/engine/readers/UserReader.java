package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.User;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("UserReader")
public class UserReader {
    @Autowired
    private UserRepository userRepository;

    public User get(Long id)
    {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found with id : " + id)
        );
    }
}
