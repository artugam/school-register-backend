package com.artur.engineer.controllers;

import com.artur.engineer.engine.managers.NotificationManager;
import com.artur.engineer.engine.managers.SubjectManager;
import com.artur.engineer.engine.readers.NotificationReader;
import com.artur.engineer.engine.readers.SubjectReader;
import com.artur.engineer.engine.views.NotificationView;
import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.engine.views.SubjectView;
import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.Notification;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.notification.NotificationCreatePayload;
import com.artur.engineer.payload.subject.SubjectConfigurationOptions;
import com.artur.engineer.payload.subject.SubjectCreate;
import com.artur.engineer.repositories.SubjectRepository;
import com.artur.engineer.security.CurrentUser;
import com.artur.engineer.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping(path = "/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationReader reader;

    @Autowired
    private NotificationManager manager;

    @GetMapping(path = "/{notificationId}")
    @JsonView({PagedView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    Notification get(@PathVariable(value = "notificationId") Long id) {
        return reader.get(id);
    }


    @PostMapping(path = "")
    @JsonView({NotificationView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    Notification create(
            @Valid @RequestBody NotificationCreatePayload payload,
            @CurrentUser UserPrincipal currentUser
    ) {
        return manager.create(payload, currentUser);
    }

    @PatchMapping(path = "/{notificationId}")
    @JsonView({SubjectView.class})
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    Notification edit(
            @PathVariable(value = "notificationId") Long id,
            @Valid @RequestBody NotificationCreatePayload payload,
            @CurrentUser UserPrincipal currentUser
    ) {
        return manager.edit(id, payload, currentUser);
    }

    @DeleteMapping(path = "/{notificationId}")
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public @ResponseBody
    ApiResponse create(
            @PathVariable(value = "notificationId") Long id
    ) {
        manager.remove(id);
        return new ApiResponse(true, "Subject has been deleted");
    }
}
