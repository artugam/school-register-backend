package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.readers.CoursesReader;
import com.artur.engineer.engine.readers.RoleReader;
import com.artur.engineer.engine.readers.UserReader;
import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.course.CourseCreate;
import com.artur.engineer.payload.user.UserCreate;
import com.artur.engineer.payload.user.UserCreateWithPassword;
import com.artur.engineer.repositories.CourseRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("CourseManager")
public class CourseManager {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CoursesReader reader;

    public Course createOrUpdate(Course course, String name, String degree, String form, int semesters) {

        course.setName(name);
        course.setDegree(degree);
        course.setForm(form);
        course.setSemesters(semesters);

        return repository.save(course);
    }

    public Course create(CourseCreate create)  {
        return this.createOrUpdate(new Course(), create.getName(), create.getDegree(), create.getForm(), create.getSemesters());
    }

    public Course edit(Long id, CourseCreate create) {
        return this.createOrUpdate(reader.get(id), create.getName(), create.getDegree(), create.getForm(), create.getSemesters());
    }

    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
