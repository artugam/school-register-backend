package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.readers.CourseGroupReader;
import com.artur.engineer.engine.readers.SubjectReader;
import com.artur.engineer.engine.readers.SubjectScheduleReader;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.subject.SubjectCreate;
import com.artur.engineer.payload.subjectSchedule.SubjectScheduleCreate;
import com.artur.engineer.repositories.SubjectRepository;
import com.artur.engineer.repositories.SubjectScheduleRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("SubjectScheduleManager")
public class SubjectScheduleManager {

    @Autowired
    private SubjectScheduleRepository repository;

    @Autowired
    private SubjectScheduleReader reader;

    @Autowired
    private SubjectReader subjectReader;

    public SubjectSchedule createOrUpdate(SubjectSchedule subjectSchedule, Date start, Date end, Long subjectId) {

        subjectSchedule.setStart(start);
        subjectSchedule.setEnd(end);
        subjectSchedule.setSubject(subjectReader.get(subjectId));

        return repository.save(subjectSchedule);
    }

    public SubjectSchedule create(SubjectScheduleCreate payload) {
        return this.createOrUpdate(new SubjectSchedule(), payload.getStart(), payload.getEnd(), payload.getSubjectId());
    }

    public SubjectSchedule edit(Long id, SubjectScheduleCreate payload) {
        return this.createOrUpdate(reader.get(id), payload.getStart(), payload.getEnd(), payload.getSubjectId());
    }

    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
