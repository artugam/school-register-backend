package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.CourseManager;
import com.artur.engineer.engine.readers.CourseGroupReader;
import com.artur.engineer.engine.readers.CoursesReader;
import com.artur.engineer.engine.readers.UserReader;
import com.artur.engineer.engine.views.*;
import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.course.CourseConfigurationResponse;
import com.artur.engineer.payload.course.CourseCreate;
import com.artur.engineer.payload.course.StudentsIds;
import com.artur.engineer.payload.user.UserIdPayload;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

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
    private UserReader userReader;

    @Autowired
    private CourseGroupReader groupReader;

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
    @JsonView({CourseView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Course create(@Valid @RequestBody CourseCreate create) {
        return manager.create(create);
    }

    @PatchMapping(path = "/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({CourseView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Course edit(@PathVariable(value = "courseId") Long id, @Valid @RequestBody CourseCreate create) {
        return manager.edit(id, create);
    }

    @DeleteMapping(path = "/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({CourseView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteCourse(@PathVariable(value = "courseId") Long id) {
        manager.remove(id);
        return true;
    }

    @GetMapping(path = "/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({CourseWithUserView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Course getCourse(@PathVariable(value = "courseId") Long id) {
        return reader.get(id);
    }

    @GetMapping(path = "/configuration/options")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CourseConfigurationResponse getConfiguration() {
        return reader.getConfiguration();
    }

    @GetMapping(path = "/{courseId}/students")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({PagedView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public PagedResponse getCourseUsers(
            @PathVariable(value = "courseId") Long id,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer records,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String search
    ) {
        return userReader.getAllUsersByCourseId(id, page, records, sortField, sortDirection, search);
    }

    @GetMapping(path = "/{courseId}/allStudents")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({PagedView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public Collection<User> getCourseUsers(
            @PathVariable(value = "courseId") Long id
    ) {
        return userReader.getAllUsersByCourseId(id);
    }

    @DeleteMapping(path = "/{courseId}/students")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({PagedView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse deleteCourseUsers(
            @PathVariable(value = "courseId") Long id,
            @RequestBody StudentsIds courseRemoveStudents
    ) {
        manager.removeStudentsFromCourse(id, courseRemoveStudents);
        return new ApiResponse(true, "Users Removed");
    }

    @PostMapping(path = "/{courseId}/students")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({ApiResponseView.class})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse addCourseUsers(
            @PathVariable(value = "courseId") Long id,
            @Valid @RequestBody StudentsIds studentsIds
    ) {
         manager.addStudentsToCourse(id, studentsIds);
         return new ApiResponse(true, "Users Added");
    }



    @GetMapping(path = "/{courseId}/notStudents")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonView({UserView.class})
    public List<User> getUsersNotInCourse(
            @PathVariable(value = "courseId") Long id
    ) {
        return userReader.getUserNotInCourse(id);
    }

    @PostMapping(path = "/{courseId}/foreman")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonView({CourseWithUserView.class})
    public Course addCourseForeman(
            @PathVariable(value = "courseId") Long id,
            @Valid @RequestBody UserIdPayload userIdPayload
    ) {
        return manager.setForeman(id, userIdPayload.getUserId());
    }

    @GetMapping(path = "/{courseId}/groups")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({PagedView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public PagedResponse getCourseGroups(
            @PathVariable(value = "courseId") Long id,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer records,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String search
    ) {
        return groupReader.getGroupsByCourseId(id, page, records, sortField, sortDirection, search);
    }
}
