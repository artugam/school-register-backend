package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.List;


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

    public PagedResponse<User> getAllUsers(int page, int size, String sortField, String direction, String search) {

        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

//        Page<User> query = this.userRepository.findByFirstNameContainingOrLastNameContainingOrEmailContaining(
//                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField)),
//                search
//        );
        Page<User> query = this.userRepository.findByFirstNameContainingOrLastNameContainingOrEmailContaining(
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField)),
                search,
                search,
                search
        );
        return new PagedResponse<>(query);
    }

    public PagedResponse<User> getAllUsersByCourseId(Long courseId, int page, int size, String sortField, String direction, String search) {
        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

        Page<User> query = userRepository.findAllByCourseId(
                courseId,
                search,
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
        );
        return new PagedResponse<>(query);
    }

    public List<User> getUserNotInCourse(Long id) {
        List<User> users =  userRepository.findAllByRolesNameIsNotIn(
                Arrays.asList(Role.ROLE_SUPER_USER, Role.ROLE_USER)
//                Arrays.asList(id)
        );

        return users;
    }
}
