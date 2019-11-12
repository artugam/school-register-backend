package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.SubjectManager;
import com.artur.engineer.engine.readers.SubjectReader;
import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.engine.views.SubjectView;
import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.course.CourseCreate;
import com.artur.engineer.payload.subject.SubjectConfigurationOptions;
import com.artur.engineer.payload.subject.SubjectCreate;
import com.artur.engineer.repositories.SubjectRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@CrossOrigin
@RequestMapping(path = "/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectManager subjectManager;

    @Autowired
    private SubjectReader subjectReader;


    @GetMapping(path = "/{subjectId}")
    @JsonView({PagedView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    Subject getSubject(@PathVariable(value = "subjectId") Long id) {
        return subjectReader.get(id);
    }


    @GetMapping(path = "/configuration/options")
    @JsonView({UserView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    SubjectConfigurationOptions getConfiguration() {
        return subjectReader.getConfiguration();
    }

    @PostMapping(path = "")
    @JsonView({SubjectView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    Subject create(
            @Valid @RequestBody SubjectCreate payload
    ) {
        return subjectManager.create(payload);
    }

    @PatchMapping(path = "/{subjectId}")
    @JsonView({SubjectView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    Subject create(
            @PathVariable(value = "subjectId") Long id,
            @Valid @RequestBody SubjectCreate payload
    ) {
        return subjectManager.edit(id, payload);
    }

    @DeleteMapping(path = "/{subjectId}")
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    ApiResponse create(
            @PathVariable(value = "subjectId") Long id
    ) {
        subjectManager.remove(id);
        return new ApiResponse(true, "Subject has been deleted");
    }

    @GetMapping(path = "/{subjectId}/schedule")
    @JsonView({PagedView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    PagedResponse<SubjectSchedule> getGroupSubjects(
            @PathVariable(value = "subjectId") Long id,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer records,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String search
    ) {
        return subjectReader.getSubjectSchedule(id, page, records, sortField, sortDirection, search);
    }


}
