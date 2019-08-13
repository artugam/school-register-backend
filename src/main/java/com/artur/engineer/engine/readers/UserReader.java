package com.artur.engineer.engine.readers;

import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("UserReader")
public class UserReader {
    @Autowired
    private UserRepository userRepository;


}
