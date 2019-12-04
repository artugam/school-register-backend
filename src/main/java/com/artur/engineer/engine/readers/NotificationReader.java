package com.artur.engineer.engine.readers;

import com.artur.engineer.entities.*;
import com.artur.engineer.payload.PagedResponse;
import com.artur.engineer.payload.subject.SubjectConfigurationOptions;
import com.artur.engineer.repositories.NotificationRepository;
import com.artur.engineer.repositories.SubjectRepository;
import com.artur.engineer.repositories.SubjectScheduleRepository;
import com.artur.engineer.repositories.UserRepository;
import com.artur.engineer.security.UserPrincipal;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.Collection;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("NotificationReader")
public class NotificationReader {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private UserReader userReader;

    public Notification get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Notification not found with id : " + id)
        );
    }

    public PagedResponse<Notification> getNotifications(Long userId, int page, int size, String sortField, String direction, String search) {

        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

        Page<Notification> query = repository.findAllNotifications(
                userId,
                search,
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
        );

        return new PagedResponse<>(query);
    }

    public PagedResponse<Notification> getNotifications(int page, int size, String sortField, String direction, String search) {

        Sort.Direction chooseDirection = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            chooseDirection = Sort.Direction.DESC;
        }

        Page<Notification> query = repository.findAllNotifications(
                search,
                PageRequest.of(page - 1, size, Sort.by(chooseDirection, sortField))
        );

        return new PagedResponse<>(query);
    }

    public PagedResponse<Notification> getMyNotifications(UserPrincipal currentUser, int page, int size) {

        User user = userReader.get(currentUser.getId());

        Page<Notification> query = repository.findAllByCourseIn(
                user.getCourses(),
                PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        );

        return new PagedResponse<>(query);
    }
}
