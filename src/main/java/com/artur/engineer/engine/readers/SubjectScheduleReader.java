package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.subject.SubjectConfigurationOptions;
import com.artur.engineer.repositories.SubjectRepository;
import com.artur.engineer.repositories.SubjectScheduleRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.Collection;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("SubjectScheduleReader")
public class SubjectScheduleReader {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository repository;

    @Autowired
    private SubjectScheduleRepository subjectScheduleRepository;

    public SubjectSchedule get(Long id) {
        return subjectScheduleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Subject not found with id : " + id)
        );
    }

    public PagedResponse<Subject> getSubjects(Long courseGroupId, int page, int size, String sortField, String direction, String search) {

        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

        Page<Subject> query = subjectRepository.findAllByCourseGroupId(
                courseGroupId,
                search,
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
        );

        return new PagedResponse<>(query);
    }

    public SubjectConfigurationOptions getConfiguration() {
        SubjectConfigurationOptions config = new SubjectConfigurationOptions();

        Collection<User> users = userRepository.findAllByRoleName(Role.ROLE_TEACHER);

        config.setTeachers(users);
        config.setTypes(Subject.ALLOWED_SUBJECT_TYPES);

        return config;
    }

    public PagedResponse<SubjectSchedule> getSubjectSchedule(Long subjectId, int page, int size, String sortField, String direction, String search) {
        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

        Page<SubjectSchedule> query = subjectScheduleRepository.findAllBySubjectId(
                subjectId,
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
        );


        return new PagedResponse<>(query);
    }
}
