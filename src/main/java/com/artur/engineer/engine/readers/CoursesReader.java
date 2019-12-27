package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.course.CourseConfigurationResponse;
import com.artur.engineer.repositories.CourseRepository;
import com.artur.engineer.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("CourseReader")
public class CoursesReader {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private UserReader userReader;

    public Course get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found with id : " + id)
        );
    }

    public PagedResponse<Course> getAll(int page, int size, String sortField, String direction, String search, UserPrincipal userPrincipal) {

        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

//        if(userPrincipal.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_USER)) &&
//                !userPrincipal.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_SUPER_USER))
//        ) {
//
//            Page<Course> query = this.repository.findForemanCourses(
//                    search,
//                    userReader.get(userPrincipal.getId()),
//                    PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
//            );
//            return new PagedResponse<>(query);
//        }

        if(userPrincipal.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_USER)) &&
            !userPrincipal.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_TEACHER))
        ) {

            User user = userReader.get(userPrincipal.getId());
            List<Long> users = new ArrayList<>(Arrays.asList(user.getId()));
            Page<Course> query = this.repository.findForemanCourses(
                    search,
//                    user,
                    users,
                    PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
            );
            return new PagedResponse<>(query);
        }

        Page<Course> query = this.repository.findToList(
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField)),
                search
        );
        return new PagedResponse<>(query);
    }

    public CourseConfigurationResponse getConfiguration() {
        CourseConfigurationResponse configuration = new CourseConfigurationResponse();
        configuration.setDegrees(Arrays.asList(Course.ALLOWED_DEGREES));
        configuration.setForms(Arrays.asList(Course.ALLOWED_FORMS));

        return configuration;
    }
}
