package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.readers.CoursesReader;
import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.course.CourseCreate;
import com.artur.engineer.payload.course.StudentsIds;
import com.artur.engineer.repositories.CourseRepository;
import com.artur.engineer.repositories.RoleRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("CourseManager")
public class CourseManager {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CoursesReader reader;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Course createOrUpdate(Course course, String name, String degree, String form, int semesters, Date startDate, int currentSemester) {

        course.setName(name);
        course.setDegree(degree);
        course.setForm(form);
        course.setSemesters(semesters);
        course.setStartDate(startDate);
        course.setCurrentSemester(currentSemester);

        return repository.save(course);
    }

    public Course create(CourseCreate create) {
        return this.createOrUpdate(new Course(), create.getName(), create.getDegree(), create.getForm(), create.getSemesters(), create.getStartDate(), 1);
    }

    public Course edit(Long id, CourseCreate create) {
        return this.createOrUpdate(reader.get(id), create.getName(), create.getDegree(), create.getForm(), create.getSemesters(), create.getStartDate(), create.getCurrentSemester());
    }

    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }

    public void removeStudentsFromCourse(Long id, StudentsIds courseRemoveStudents) {

        List<User> users = userRepository.findAllByIdIn(courseRemoveStudents.getStudentsIds());

        Optional<Course> courseOptional = repository.findById(id);
        Course course = courseOptional.get();
        course.removeUsers(users);
        repository.save(course);
    }

    public void addStudentsToCourse(Long id, StudentsIds courseRemoveStudents) {

        List<User> users = userRepository.findAllByIdIn(courseRemoveStudents.getStudentsIds());
        Optional<Course> courseOptional = repository.findById(id);

        Course course = courseOptional.get();
        course.addUsers(users);
        repository.save(course);
    }

    public Course setForeman(Long courseId, Long userId) {
        User user = userRepository.findById(userId).get();
        Course course = repository.findById(courseId).get();

        Role role = roleRepository.findByName(Role.ROLE_SUPER_USER);
        User foreman = null;
        if(null != course.getForeman() && course.getForeman().getForemanCourses().size() < 2) {
            foreman = course.getForeman();
            foreman.removeRole(role);
            foreman.removeForemanCourse(course);
        }


        user.addRole(role);

        if(null != foreman) {
            userRepository.save(foreman);
        }
        course.setForeman(user);
        repository.save(course);



        return course;
    }

    public Course removeForeman(Long courseId)
    {
        Course course = repository.findById(courseId).get();
        course.setForeman(null);

        return course;
    }
}
