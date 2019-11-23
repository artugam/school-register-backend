package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.course.CourseConfigurationResponse;
import com.artur.engineer.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.Arrays;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("CourseReader")
public class CoursesReader {

    @Autowired
    private CourseRepository repository;

    public Course get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found with id : " + id)
        );
    }

    public PagedResponse<Course> getAll(int page, int size, String sortField, String direction, String search) {

        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

//        Page<Course> query = this.repository.findByNameContainingOrFormContainingOrDegreeContainingOrSemestersContainingOrStartDateContaining(
        Page<Course> query = this.repository.findByNameContainingOrFormContainingOrDegreeContaining(
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField)),
                search,
                search,
                search
        );
        return new PagedResponse<>(query);
    }

//    publics PagedResponse<UserIdPayload> getStudents(Long courseId, int page, int size, String sortField, String direction, String search) {
//        Sort.Direction chooseDirection = Sort.Direction.ASC;
//        if (direction.equals("DESC")) {
//            chooseDirection = Sort.Direction.DESC;
//        }
//
//        Page<UserIdPayload> query = this.repository.findStudentsByCourseId(
//                courseId,
//                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
//
////                ,
////                search,
////                search
//        );
//        return new PagedResponse<>(query);
//    }

    public CourseConfigurationResponse getConfiguration() {
        CourseConfigurationResponse configuration = new CourseConfigurationResponse();
        configuration.setDegrees(Arrays.asList(Course.ALLOWED_DEGREES));
        configuration.setForms(Arrays.asList(Course.ALLOWED_FORMS));

        return configuration;
    }
}
