package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.SubjectScheduleManager;
import com.artur.engineer.engine.readers.SubjectPresenceReader;
import com.artur.engineer.engine.readers.SubjectScheduleReader;
import com.artur.engineer.engine.views.SubjectPresenceView;
import com.artur.engineer.engine.views.SubjectScheduleView;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.presence.SubjectPresenceConfigurationOptions;
import com.artur.engineer.payload.subjectSchedule.SubjectScheduleCreate;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.POST;

@Controller
@CrossOrigin
@RequestMapping(path = "/api/presence")
public class SubjectPresenceController {

    @Autowired
    private SubjectScheduleManager manager;

    @Autowired
    private SubjectPresenceReader reader;


    @GetMapping(path = "/configuration/options")
    @JsonView({SubjectPresenceView.class})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public @ResponseBody
    SubjectPresenceConfigurationOptions getConfiguration() {
        return reader.getConfiguration();
    }


//    @PostMapping(path = "")
//    @JsonView({SubjectScheduleView.class})
//    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
//    public @ResponseBody
//    SubjectSchedule create(
//            @Valid @RequestBody SubjectScheduleCreate payload
//    ) {
//        return manager.create(payload);
//    }
//
//    @PatchMapping(path = "/{scheduleId}")
//    @JsonView({SubjectScheduleView.class})
//    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
//    public @ResponseBody
//    SubjectSchedule create(
//            @PathVariable(value = "scheduleId") Long id,
//            @Valid @RequestBody SubjectScheduleCreate payload
//    ) {
//        return manager.edit(id, payload);
//    }
//
//    @DeleteMapping(path = "/{scheduleId}")
//    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
//    public @ResponseBody
//    ApiResponse create(
//            @PathVariable(value = "scheduleId") Long id
//    ) {
//        manager.remove(id);
//        return new ApiResponse(true, "Subject has been deleted");
//    }
}
