package com.artur.engineer.controllers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.managers.UserManager;
import com.artur.engineer.engine.readers.NotificationReader;
import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.Notification;
import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.JwtAuthenticationResponse;
import com.artur.engineer.payload.LoginRequest;
import com.artur.engineer.payload.PagedResponse;
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
import java.util.Optional;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/api/auth")
public class AuthenticationController {

    @Autowired
    UserManager userManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    NotificationReader notificationReader;

    @PostMapping(path = "/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.class})
    public User registerUser(@Valid @RequestBody UserCreateWithPassword userCreateRequest) throws ApiException {

        return userManager.create(userCreateRequest);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(path = "/me")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({UserView.class})
    public Optional<User> me(@CurrentUser UserPrincipal currentUser) {
        return userRepository.findById(currentUser.getId());
    }

    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    @GetMapping(path = "/notifications")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({PagedView.class})
    public PagedResponse<Notification> me(
            @CurrentUser UserPrincipal currentUser,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer records,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String search
    ) {
        if(currentUser.getAuthorities().contains(Role.ROLE_ADMIN)) {
            return notificationReader.getNotifications(page, records, sortField, sortDirection, search);
        }
        return notificationReader.getNotifications(currentUser.getId(), page, records, sortField, sortDirection, search);
    }
}
