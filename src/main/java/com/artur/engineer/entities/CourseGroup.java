package com.artur.engineer.entities;

import com.artur.engineer.engine.views.CourseGroupUsersView;
import com.artur.engineer.engine.views.CourseGroupView;
import com.artur.engineer.engine.views.SubjectScheduleFullView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "course_id"})
})
public class CourseGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({CourseGroupView.class, SubjectScheduleFullView.class})
    private Long id;


    @JsonView({CourseGroupView.class, SubjectScheduleFullView.class})
    private String name;

    @ManyToOne
    @JoinColumn
    @JsonView({CourseGroupView.class, SubjectScheduleFullView.class})
    private Course course;

    @JsonView({CourseGroupUsersView.class})
    @ManyToMany(mappedBy = "groups", cascade = CascadeType.ALL)
    private Collection<User> users = new HashSet<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Subject> subjects;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Notification> notifications;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        course.addGroup(this);
    }

    public Collection<User> getUsers() {
        return users;
    }


    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
            user.addGroup(this);
        }
    }

    public void removeUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            user.removeGroup(this);
        }
    }

    public Collection<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addNotification(Notification notification) {
        if(!this.notifications.contains(notification)) {
            this.notifications.add(notification);
            notification.setGroup(this);
        }
    }

    public void removeNotification(Notification notification) {
        if(this.notifications.contains(notification)) {
            this.notifications.remove(notification);
            notification.setGroup(null);
        }
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;

    }
}
