package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.readers.CourseGroupReader;
import com.artur.engineer.engine.readers.SubjectReader;
import com.artur.engineer.engine.readers.SubjectScheduleReader;
import com.artur.engineer.engine.readers.UserReader;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.SubjectPresence;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.subject.SubjectCreate;
import com.artur.engineer.payload.subjectSchedule.SubjectScheduleCreate;
import com.artur.engineer.payload.subjectSchedule.SubjectSchedulePresencePayload;
import com.artur.engineer.repositories.SubjectRepository;
import com.artur.engineer.repositories.SubjectScheduleRepository;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

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
    private UserReader userReader;

    @Autowired
    private SubjectReader subjectReader;

    public SubjectSchedule createOrUpdate(SubjectSchedule subjectSchedule, Date start, Date end, Long subjectId, String description) {

        subjectSchedule.setStart(start);
        subjectSchedule.setEnd(end);
        subjectSchedule.setSubject(subjectReader.get(subjectId));
        subjectSchedule.setDescription(description);

        return repository.save(subjectSchedule);
    }

    public SubjectSchedule create(SubjectScheduleCreate payload) {

        SubjectSchedule schedule = null;
        Date start = payload.getStart();
        Date end = payload.getEnd();
        for (int i = 0; i < payload.getAmount(); i++) {

            schedule = this.createOrUpdate(new SubjectSchedule(), start, end, payload.getSubjectId(), payload.getDescription());

            if (!Arrays.asList(SubjectScheduleCreate.ALLOWED_FREQUENCIES).contains(payload.getFrequency())) {
                return schedule;
            }


            int days = payload.getFrequency().equals(SubjectScheduleCreate.FREQUENCY_ONE_WEEK) ? 7 : 14;

            // convert date to calendar
            Calendar c = Calendar.getInstance();
            c.setTime(start);
            c.add(Calendar.DAY_OF_MONTH, days);
            start = c.getTime();

            c.setTime(end);
            c.add(Calendar.DAY_OF_MONTH, days);
            end = c.getTime();
        }

        return schedule;
    }

    public SubjectSchedule edit(Long id, SubjectScheduleCreate payload) {
        return this.createOrUpdate(reader.get(id), payload.getStart(), payload.getEnd(), payload.getSubjectId(), payload.getDescription());
    }

    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }

    public SubjectSchedule addPresence(Long id, List<SubjectSchedulePresencePayload> payload) {

        SubjectSchedule schedule = reader.get(id);

        for (SubjectSchedulePresencePayload presence : payload) {
            SubjectPresence subjectPresence = new SubjectPresence();
            subjectPresence.setUser(userReader.get(presence.getUserId()));
            subjectPresence.setPresenceStatus(presence.getValue());

            schedule.addPresence(subjectPresence);
        }
        repository.save(schedule);

        return schedule;
    }
}
