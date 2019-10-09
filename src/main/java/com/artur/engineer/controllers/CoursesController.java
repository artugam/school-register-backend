package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.CourseManager;
import com.artur.engineer.engine.readers.CoursesReader;
import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.Course;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.course.CourseConfigurationResponse;
import com.artur.engineer.payload.course.CourseCreate;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/api/courses")
public class CoursesController {

    @Autowired
    private CoursesReader reader;

    @Autowired
    private CourseManager manager;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({PagedView.class})
    public PagedResponse<Course> getAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer records,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String search
    ) {
        return reader.getAll(page, records, sortField, sortDirection, search);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Course create(@Valid @RequestBody CourseCreate create) {
        return manager.create(create);
    }

    @PatchMapping(path = "/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Course edit(@PathVariable(value = "courseId") Long id, @Valid @RequestBody CourseCreate create) {
        return manager.edit(id, create);
    }

    @DeleteMapping(path = "/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteUser(@PathVariable(value = "courseId") Long id) {
        manager.remove(id);
        return true;
    }

    @GetMapping(path = "/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Course getUser(@PathVariable(value = "courseId") Long id) {
        return reader.get(id);
    }

    @GetMapping(path = "/configuration/options")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CourseConfigurationResponse getConfiguration()
    {
        return reader.getConfiguration();
    }
}
