package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.Grade;
import com.artur.engineer.entities.SubjectPresence;
import com.artur.engineer.payload.grade.GradeConfigurationOptions;
import com.artur.engineer.payload.presence.SubjectPresenceConfigurationOptions;
import com.artur.engineer.repositories.GradeRepository;
import com.artur.engineer.repositories.SubjectPresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("GradeReader")
public class GradeReader {


    @Autowired
    private GradeRepository repository;

    public Grade get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Grade not found with id : " + id)
        );
    }

    public GradeConfigurationOptions getConfiguration() {

        GradeConfigurationOptions config = new GradeConfigurationOptions();
        config.setOptions(Grade.ALLOWED_GRADES);

        return config;
    }
}
