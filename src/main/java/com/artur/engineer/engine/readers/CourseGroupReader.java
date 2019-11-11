package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.*;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("CourseGroupReader")
public class CourseGroupReader {

    @Autowired
    private CourseGroupRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public CourseGroup get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("UserIdPayload not found with id : " + id)
        );
    }


    public PagedResponse<User> getStudents(Long courseGroupId, int page, int size, String sortField, String direction, String search) {

        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

        Page<User> query = userRepository.findAllByCourseGroupId(
                courseGroupId,
                search,
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
        );

        return new PagedResponse<>(query);
    }

    public PagedResponse<CourseGroup> getGroupsByCourseId(Long courseId, int page, int size, String sortField, String direction, String search) {
        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

        Page<CourseGroup> query = repository.findAllByCourseId(
                courseId,
                search,
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
        );


        return new PagedResponse<>(query);
    }
}
