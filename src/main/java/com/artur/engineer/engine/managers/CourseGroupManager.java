package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.readers.CourseGroupReader;
import com.artur.engineer.engine.readers.CoursesReader;
import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.CourseGroup;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.course.CourseCreate;
import com.artur.engineer.payload.course.StudentsIds;
import com.artur.engineer.payload.groups.GroupCreatePayload;
import com.artur.engineer.repositories.CourseGroupRepository;
import com.artur.engineer.repositories.CourseRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("CourseGroupManager")
public class CourseGroupManager {

    @Autowired
    private CourseGroupRepository repository;

    @Autowired
    private CourseGroupReader reader;

    @Autowired
    private CoursesReader courseReader;

    @Autowired
    private UserRepository userRepository;

    public CourseGroup createOrUpdate(CourseGroup courseGroup, String name, Long courseId) {

        courseGroup.setName(name);
        courseGroup.setCourse(courseReader.get(courseId));

        return repository.save(courseGroup);
    }

    public CourseGroup create(GroupCreatePayload payload) {
        return this.createOrUpdate(new CourseGroup(), payload.getName(), payload.getCourseId());
    }

    public CourseGroup edit(Long id, GroupCreatePayload payload) {
        return this.createOrUpdate(reader.get(id), payload.getName(), payload.getCourseId());
    }

    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }

    public CourseGroup setStudents(Long id, StudentsIds payload) {

        CourseGroup courseGroup = reader.get(id);
        for (User u : userRepository.findAllByIdIn(payload.getStudentsIds())) {
            courseGroup.addUser(u);
        }

        repository.save(courseGroup);

        return courseGroup;
    }

}
