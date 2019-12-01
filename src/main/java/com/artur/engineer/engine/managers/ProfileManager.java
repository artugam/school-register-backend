package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.readers.UserReader;
import com.artur.engineer.entities.Notification;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.profile.ProfileUpdatePayload;
import com.artur.engineer.payload.publics.PasswordSetPayload;
import com.artur.engineer.repositories.SubjectRepository;
import com.artur.engineer.repositories.UserRepository;
import com.artur.engineer.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("ProfileManager")
public class ProfileManager {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserReader userReader;

    @Autowired
    public SubjectRepository subjectRepository;

    public User update(UserPrincipal userPrincipal, ProfileUpdatePayload payload) {

        User user = userReader.get(userPrincipal.getId());
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        userRepository.save(user);

        return user;
    }

    public PagedResponse<Subject> getProfileSubjects(UserPrincipal userPrincipal, int page, int size, String sortField, String direction, String search) {
        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

        Page<Subject> query = subjectRepository.findAllSubjectByUserId(
                userPrincipal.getId(),
                search,
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
        );

        return new PagedResponse<>(query);
    }

}
