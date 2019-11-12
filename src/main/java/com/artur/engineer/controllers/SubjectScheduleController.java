package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.SubjectManager;
import com.artur.engineer.engine.managers.SubjectScheduleManager;
import com.artur.engineer.engine.readers.SubjectReader;
import com.artur.engineer.engine.readers.SubjectScheduleReader;
import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.engine.views.SubjectScheduleView;
import com.artur.engineer.engine.views.SubjectView;
import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.subject.SubjectConfigurationOptions;
import com.artur.engineer.payload.subject.SubjectCreate;
import com.artur.engineer.payload.subjectSchedule.SubjectScheduleCreate;
import com.artur.engineer.repositories.SubjectRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping(path = "/api/schedule")
public class SubjectScheduleController {

    @Autowired
    private SubjectScheduleManager manager;

    @Autowired
    private SubjectScheduleReader reader;


    @GetMapping(path = "/{scheduleId}")
    @JsonView({SubjectScheduleView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    SubjectSchedule getSubject(@PathVariable(value = "scheduleId") Long id) {
        return reader.get(id);
    }

    @PostMapping(path = "")
    @JsonView({SubjectScheduleView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    SubjectSchedule create(
            @Valid @RequestBody SubjectScheduleCreate payload
    ) {
        return manager.create(payload);
    }

    @PatchMapping(path = "/{scheduleId}")
    @JsonView({SubjectScheduleView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    SubjectSchedule create(
            @PathVariable(value = "scheduleId") Long id,
            @Valid @RequestBody SubjectScheduleCreate payload
    ) {
        return manager.edit(id, payload);
    }

    @DeleteMapping(path = "/{scheduleId}")
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    ApiResponse create(
            @PathVariable(value = "scheduleId") Long id
    ) {
        manager.remove(id);
        return new ApiResponse(true, "Subject has been deleted");
    }
}
