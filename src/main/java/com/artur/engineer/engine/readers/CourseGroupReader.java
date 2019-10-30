package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.CourseGroup;
import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.repositories.CourseGroupRepository;
import com.artur.engineer.repositories.CourseRepository;
import com.artur.engineer.repositories.RoleRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("CourseGroupReader")
public class CourseGroupReader {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseGroupRepository repository;

    @Autowired
    private UserRepository userRepository;

    public CourseGroup get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("UserIdPayload not found with id : " + id)
        );
    }

    public PagedResponse<User> getAllUsers(int page, int size, String sortField, String direction, String search) {

        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

        Page<User> query = this.userRepository.findByFirstNameContainingOrLastNameContainingOrEmailContaining(
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField)),
                search,
                search,
                search
        );
        return new PagedResponse<>(query);
    }
}
