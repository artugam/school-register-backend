package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.readers.CourseGroupReader;
import com.artur.engineer.engine.readers.SubjectReader;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.subject.SubjectCreate;
import com.artur.engineer.repositories.SubjectRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("SubjectManager")
public class SubjectManager {

    @Autowired
    private SubjectRepository repository;

    @Autowired
    private SubjectReader reader;

    @Autowired
    private CourseGroupReader groupReader;

    @Autowired
    private UserRepository userRepository;

    public Subject createOrUpdate(Subject subject, String name, Long courseGroupId, Double hours, String type, List<Long> teacherIds) {

        subject.setName(name);
        subject.setGroup(groupReader.get(courseGroupId));
        subject.setHours(hours);
        subject.setType(type);

        List<User> users = userRepository.findAllByIdIn(teacherIds);

        subject.setTeachers(new ArrayList<>());
        for (User u: users) {
            subject.addTeacher(u);
        }

        return repository.save(subject);
    }

    public Subject create(SubjectCreate payload) {
        return this.createOrUpdate(new Subject(), payload.getName(), payload.getGroupId(), payload.getHours(), payload.getType(), payload.getTeachersIds());
    }

    public Subject edit(Long id, SubjectCreate payload) {
        return this.createOrUpdate(reader.get(id), payload.getName(), payload.getGroupId(), payload.getHours(), payload.getType(), payload.getTeachersIds());
    }

    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }

}
