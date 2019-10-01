package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.User;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("UserReader")
public class UserReader {

    @Autowired
    private UserRepository userRepository;

    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found with id : " + id)
        );
    }

    public PagedResponse<User> getAllUsers(int page, int size, String sortField, String direction) {

        Sort.Direction chooseDirection = Sort.Direction.DESC;
        if (direction.equals("ASC")) {
            chooseDirection = Sort.Direction.ASC;
        }

        Page<User> usersQuery = this.userRepository.findAll(PageRequest.of(page, size, Sort.by(chooseDirection, sortField)));
        PagedResponse<User> response = new PagedResponse<>(
                usersQuery.getContent(),
                usersQuery.getNumber(),
                usersQuery.getSize(),
                usersQuery.getTotalElements(),
                usersQuery.getTotalPages(),
                usersQuery.isLast()
        );

        return response;
    }
}
