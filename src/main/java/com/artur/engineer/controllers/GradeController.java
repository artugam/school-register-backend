package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.GradeManager;
import com.artur.engineer.engine.managers.SubjectPresenceManager;
import com.artur.engineer.engine.readers.GradeReader;
import com.artur.engineer.engine.readers.SubjectPresenceReader;
import com.artur.engineer.engine.views.ApiResponseView;
import com.artur.engineer.engine.views.GradeView;
import com.artur.engineer.engine.views.SubjectPresenceView;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.grade.GradeConfigurationOptions;
import com.artur.engineer.payload.grade.GradesUpdatePayload;
import com.artur.engineer.payload.presence.SubjectPresenceConfigurationOptions;
import com.artur.engineer.payload.presence.SubjectPresenceUpdate;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(path = "/api/grades")
public class GradeController {

    @Autowired
    private GradeManager manager;

    @Autowired
    private GradeReader reader;


    @GetMapping(path = "/configuration/options")
    @JsonView({GradeView.class})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public @ResponseBody
    GradeConfigurationOptions getConfiguration() {
        return reader.getConfiguration();
    }


    @PostMapping(path = "/update/all")
    @JsonView({ApiResponseView.class})
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public @ResponseBody
    ApiResponse updatePresences(
            @Valid @RequestBody List<GradesUpdatePayload> payload
    ) {
        manager.update(payload);

        return new ApiResponse(true, "Updated");
    }

}
