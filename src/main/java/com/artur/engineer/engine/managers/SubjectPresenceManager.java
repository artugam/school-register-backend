package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.readers.CourseGroupReader;
import com.artur.engineer.engine.readers.SubjectPresenceReader;
import com.artur.engineer.engine.readers.SubjectReader;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.SubjectPresence;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.presence.SubjectPresenceUpdate;
import com.artur.engineer.payload.subject.SubjectCreate;
import com.artur.engineer.repositories.SubjectPresenceRepository;
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
@Component("SubjectPresenceManager")
public class SubjectPresenceManager {

    @Autowired
    private SubjectPresenceReader reader;

    @Autowired
    private SubjectPresenceRepository repository;

    public void updatePresences(List<SubjectPresenceUpdate> updates) {

        List<SubjectPresence> toSave = new ArrayList<>();
        for (SubjectPresenceUpdate update : updates) {
            SubjectPresence presence = reader.get(update.getSchedulePresenceId());
            presence.setPresenceStatus(update.getStatus());
            toSave.add(presence);
        }

        repository.saveAll(toSave);
    }
}
