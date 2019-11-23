package com.artur.engineer.controllers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.managers.PublicManager;
import com.artur.engineer.engine.managers.UserManager;
import com.artur.engineer.engine.readers.NotificationReader;
import com.artur.engineer.engine.readers.SubjectScheduleReader;
import com.artur.engineer.engine.views.ApiResponseView;
import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.engine.views.SubjectScheduleFullView;
import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.Notification;
import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.JwtAuthenticationResponse;
import com.artur.engineer.payload.LoginRequest;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.notification.NotificationCreatePayload;
import com.artur.engineer.payload.publics.PasswordResetPayload;
import com.artur.engineer.payload.publics.PasswordSetPayload;
import com.artur.engineer.payload.user.UserCreateWithPassword;
import com.artur.engineer.repositories.RoleRepository;
import com.artur.engineer.repositories.UserRepository;
import com.artur.engineer.security.CurrentUser;
import com.artur.engineer.security.JwtTokenProvider;
import com.artur.engineer.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/api/public")
public class PublicController {

    @Autowired
    PublicManager manager;


    @PostMapping(path = "/password/reset")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({ApiResponseView.class})
    public ApiResponse passwordReset (
            @Valid @RequestBody PasswordResetPayload payload
    ) {
        manager.resetPassword(payload.getEmail());
        return new ApiResponse(true, "Email send");
    }

    @PostMapping(path = "/password/set")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({ApiResponseView.class})
    public ApiResponse passwordSet (
            @Valid @RequestBody PasswordSetPayload payload
    ) {
        manager.setPassword(payload);
        return new ApiResponse(true, "Password has been changed");
    }
}

