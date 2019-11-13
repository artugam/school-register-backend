package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.readers.*;
import com.artur.engineer.entities.CourseGroup;
import com.artur.engineer.entities.Notification;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.course.StudentsIds;
import com.artur.engineer.payload.groups.GroupCreatePayload;
import com.artur.engineer.payload.notification.NotificationCreatePayload;
import com.artur.engineer.repositories.CourseGroupRepository;
import com.artur.engineer.repositories.NotificationRepository;
import com.artur.engineer.repositories.UserRepository;
import com.artur.engineer.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("NotificationManager")
public class NotificationManager {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private NotificationReader reader;

    @Autowired
    private UserReader userReader;

    @Autowired
    private CoursesReader courseReader;

    @Autowired
    private CourseGroupReader courseGroupReader;

    @Autowired
    private SubjectReader subjectReader;

    public Notification createOrUpdate(Notification notification, User user, String description, Long courseId, Long courseGroupId, Long subjectId) {

        notification.setDescription(description);

        notification.setCourse(courseReader.get(courseId));
        if (null != courseGroupId) {
            notification.setGroup(courseGroupReader.get(courseGroupId));
        }
        if (null != subjectId) {
            notification.setSubject(subjectReader.get(subjectId));
        }

        notification.setCreator(user);

        return repository.save(notification);
    }

    public Notification create(NotificationCreatePayload payload, UserPrincipal currentUser) {
        return this.createOrUpdate(new Notification(), userReader.get(currentUser.getId()), payload.getDescription(), payload.getCourseId(), payload.getGroupId(), payload.getSubjectId());
    }

    public Notification edit(Long id, NotificationCreatePayload payload, UserPrincipal currentUser) {
        return this.createOrUpdate(reader.get(id), userReader.get(currentUser.getId()), payload.getDescription(), payload.getCourseId(), payload.getGroupId(), payload.getSubjectId());
    }

    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
