package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.*;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.subject.SubjectConfigurationOptions;
import com.artur.engineer.repositories.SubjectRepository;
import com.artur.engineer.repositories.SubjectScheduleRepository;
import com.artur.engineer.repositories.UserRepository;
import com.artur.engineer.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.*;


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

    @Autowired
    private UserReader userReader;

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

    public Collection<SubjectSchedule> getScheduleSubjects(UserPrincipal currentUser, Date start, Date end) {

        User user = userReader.get(currentUser.getId());

        if(currentUser.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_TEACHER))) {
            List<User> teachers = new ArrayList<>();
            teachers.add(user);

            return subjectScheduleRepository.getScheduleForTeachers(
                    teachers,
                    start,
                    end,
                    Sort.by(Sort.Direction.ASC, "start"
                    ));
        }

        if(user.getGroups().isEmpty()) {
            return new ArrayList<SubjectSchedule>();
        }


        return subjectScheduleRepository.getScheduleForGroups(
                user.getGroups(),
                start,
                end,
                Sort.by(Sort.Direction.ASC, "start"
                ));
    }
}
