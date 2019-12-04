package com.artur.engineer.entities;

import com.artur.engineer.engine.views.NotificationView;
import com.artur.engineer.engine.views.SubjectView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({NotificationView.class})
    private Long id;

    @JsonView({NotificationView.class})
    private String description;

    @ManyToOne
    @JoinColumn
    @JsonView({NotificationView.class})
    private Course course;

    @ManyToOne
    @JoinColumn
    @JsonView({NotificationView.class})
    private User creator;

    @ManyToOne
    @JoinColumn
    @JsonView({NotificationView.class})
    private CourseGroup group;

    @ManyToOne
    @JoinColumn
    @JsonView({NotificationView.class})
    private Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if(null != this.course) {
            this.course.removeNotification(this);
        }
        this.course = course;
    }

    public CourseGroup getGroup() {
        return group;
    }

    public void setGroup(CourseGroup group) {
        if(null != this.group) {
            this.group.removeNotification(this);
        }

        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        if(null != this.subject) {
            this.subject.removeNotification(this);
        }
        this.subject = subject;

    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        if(null != this.creator) {
            this.creator.removeNotification(this);
        }
        this.creator = creator;
    }
}
