package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.*;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.presence.SubjectPresenceConfigurationOptions;
import com.artur.engineer.payload.subject.SubjectConfigurationOptions;
import com.artur.engineer.repositories.SubjectPresenceRepository;
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
@Component("SubjectPresenceReader")
public class SubjectPresenceReader {


    @Autowired
    private SubjectPresenceRepository repository;

    public SubjectPresence get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Subject presence not found with id : " + id)
        );
    }

    public SubjectPresenceConfigurationOptions getConfiguration() {

        SubjectPresenceConfigurationOptions options = new SubjectPresenceConfigurationOptions();
        options.setTypes(SubjectPresence.ALLOWED_STATUS_PRESENCE);

        return options;
    }
}
