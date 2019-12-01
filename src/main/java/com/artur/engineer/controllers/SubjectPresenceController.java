package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.SubjectPresenceManager;
import com.artur.engineer.engine.managers.SubjectScheduleManager;
import com.artur.engineer.engine.readers.SubjectPresenceReader;
import com.artur.engineer.engine.readers.SubjectScheduleReader;
import com.artur.engineer.engine.views.ApiResponseView;
import com.artur.engineer.engine.views.SubjectPresenceView;
import com.artur.engineer.engine.views.SubjectScheduleView;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.presence.SubjectPresenceConfigurationOptions;
import com.artur.engineer.payload.presence.SubjectPresenceUpdate;
import com.artur.engineer.payload.subjectSchedule.SubjectScheduleCreate;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.POST;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(path = "/api/presence")
public class SubjectPresenceController {

    @Autowired
    private SubjectPresenceManager manager;

    @Autowired
    private SubjectPresenceReader reader;


    @GetMapping(path = "/configuration/options")
    @JsonView({SubjectPresenceView.class})
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody
    SubjectPresenceConfigurationOptions getConfiguration() {
        return reader.getConfiguration();
    }


    @PostMapping(path = "/update/all")
    @JsonView({ApiResponseView.class})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public @ResponseBody
    ApiResponse updatePresences(
            @Valid @RequestBody List<SubjectPresenceUpdate> payload
    ) {
        manager.updatePresences(payload);

        return new ApiResponse(true, "Updated");
    }

}
