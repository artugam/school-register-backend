package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.SubjectManager;
import com.artur.engineer.engine.readers.SubjectReader;
import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.engine.views.SubjectView;
import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.course.CourseCreate;
import com.artur.engineer.payload.subject.SubjectConfigurationOptions;
import com.artur.engineer.payload.subject.SubjectCreate;
import com.artur.engineer.repositories.SubjectRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path="/configuration/options")
    @JsonView({UserView.class})
    public @ResponseBody
    SubjectConfigurationOptions getConfiguration() {
        return subjectReader.getConfiguration();
    }

    @PostMapping(path = "")
    @JsonView({SubjectView.class})
    public @ResponseBody
    Subject create(
            @Valid @RequestBody SubjectCreate payload
    ) {
        return subjectManager.create(payload);
    }

    @PatchMapping(path = "/{subjectId}")
    @JsonView({SubjectView.class})
    public @ResponseBody
    Subject create(
            @PathVariable(value = "subjectId") Long id,
            @Valid @RequestBody SubjectCreate payload
    ) {
        return subjectManager.edit(id, payload);
    }

    @DeleteMapping(path = "/{subjectId}")
    public @ResponseBody
    ApiResponse create(
            @PathVariable(value = "subjectId") Long id
    ) {
        subjectManager.remove(id);
        return new ApiResponse(true, "Subject has been deleted");
    }
}
