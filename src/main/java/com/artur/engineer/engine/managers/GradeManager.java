package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.readers.GradeReader;
import com.artur.engineer.engine.readers.SubjectPresenceReader;
import com.artur.engineer.entities.Grade;
import com.artur.engineer.entities.SubjectPresence;
import com.artur.engineer.payload.grade.GradesUpdatePayload;
import com.artur.engineer.payload.presence.SubjectPresenceUpdate;
import com.artur.engineer.repositories.GradeRepository;
import com.artur.engineer.repositories.SubjectPresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("GradeManager")
public class GradeManager {

    @Autowired
    private GradeReader reader;

    @Autowired
    private GradeRepository repository;

    public void update(List<GradesUpdatePayload> updates) {

        List<Grade> toSave = new ArrayList<>();
        for (GradesUpdatePayload update : updates) {
            Grade grade = reader.get(update.getId());
            grade.setGrade(update.getGrade());
            toSave.add(grade);
        }

        repository.saveAll(toSave);
    }
}
