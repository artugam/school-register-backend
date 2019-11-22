package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.SubjectManager;
import com.artur.engineer.engine.readers.SubjectReader;
import com.artur.engineer.engine.views.ApiResponseView;
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
import com.artur.engineer.payload.subject.SubjectGradeNameUpdatePayload;
import com.artur.engineer.payload.subject.SubjectGradeSection;
import com.artur.engineer.payload.subjectSchedule.FullScheduleResponse;
import com.artur.engineer.payload.subjectSchedule.grades.FullGradesResponse;
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

    @GetMapping(path = "/{subjectId}/schedule/full")
    @JsonView({PagedView.class})
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody
    FullScheduleResponse fullSchedule(
            @PathVariable(value = "subjectId") Long id
    ) {
        return subjectReader.getSubjectFullSchedule(id);
    }

    @GetMapping(path = "/{subjectId}/grades/full")
    @JsonView({PagedView.class})
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody
    FullGradesResponse fullGradesSchedule(
            @PathVariable(value = "subjectId") Long id
    ) {
        return subjectReader.getSubjectFullGrades(id);
    }

    @PostMapping(path = "/{subjectId}/grades/section")
    @JsonView({ApiResponseView.class})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public @ResponseBody
    ApiResponse addGradesSection(
            @PathVariable(value = "subjectId") Long id,
            @Valid @RequestBody SubjectGradeSection payload
    ) {
        subjectManager.addGradeSection(id, payload);
        return new ApiResponse(true, "Section is added");
    }

    @PostMapping(path = "/{subjectId}/grades/name")
    @JsonView({ApiResponseView.class})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public @ResponseBody
    ApiResponse updateGradesName(
            @PathVariable(value = "subjectId") Long id,
            @Valid @RequestBody SubjectGradeNameUpdatePayload payload
    ) {
        subjectManager.updateGradeSection(id, payload);
        return new ApiResponse(true, "Name is updated");
    }

}
