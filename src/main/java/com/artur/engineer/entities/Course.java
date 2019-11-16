package com.artur.engineer.entities;

import com.artur.engineer.engine.views.CourseView;
import com.artur.engineer.engine.views.CourseWithUserView;
import com.artur.engineer.engine.views.SubjectScheduleFullView;
import com.artur.engineer.engine.views.UserView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "form", "degree", "startDate"})
})
public class Course extends BaseEntity {

    public static final String FORM_STATIC = "Stacjonarne";
    public static final String FORM_UNSTATIC = "Niestacjonarne";

    public static final String ALLOWED_FORMS[] = {
            FORM_STATIC,
            FORM_UNSTATIC
    };

    public static final String DEGREE_I = "I Stopnia";
    public static final String DEGREE_II = "II Stopnia";
    public static final String DEGREE_III = "III Stopnia";

    public static final String ALLOWED_DEGREES[] = {
            DEGREE_I,
            DEGREE_II,
            DEGREE_III
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({CourseView.class, CourseWithUserView.class})
    private Long id;

    @JsonView({CourseView.class, CourseWithUserView.class, SubjectScheduleFullView.class})
    private String name;

    @JsonView({CourseView.class, CourseWithUserView.class, SubjectScheduleFullView.class})
    private String form;

    @JsonView({CourseView.class, CourseWithUserView.class, SubjectScheduleFullView.class})
    private String degree;

    @JsonView({CourseView.class, CourseWithUserView.class})
    private int semesters;

    @JsonView({CourseView.class, CourseWithUserView.class, SubjectScheduleFullView.class})
    private Date startDate = new Date();

    @JsonView({CourseView.class, CourseWithUserView.class})
    private int currentSemester;

    @ManyToOne
    @JsonView({CourseView.class, CourseWithUserView.class})
    private User foreman;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private Collection<User> users = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Collection<CourseGroup> groups;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
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

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getSemesters() {
        return semesters;
    }

    public void setSemesters(int semesters) {
        this.semesters = semesters;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void removeUsers(Collection<User> users) {
        for (User user : users) {
            this.removeUser(user);
        }
    }

    public void removeUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            user.removeCourse(this);
        }
    }

    public void addUsers(Collection<User> users) {
        for (User user : users) {
            this.addUser(user);
        }
    }

    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
            user.addCourse(this);
        }
    }



    public User getForeman() {
        return foreman;
    }

    public void setForeman(User foreman) {
        this.foreman = foreman;
        foreman.addCourse(this);
    }


    public Collection<CourseGroup> getGroups() {
        return groups;
    }

    public void setGroups(Collection<CourseGroup> groups) {
        this.groups = groups;
    }

    public void addGroup(CourseGroup group) {
        if (!this.groups.contains(group)) {
            this.groups.add(group);
            group.setCourse(this);
        }
    }

    public void removeGroup(CourseGroup group) {
        if (this.groups.contains(group)) {
            this.groups.remove(group);
            group.setCourse(null);
        }
    }

    public void addNotification(Notification notification) {
        if(!this.notifications.contains(notification)) {
            this.notifications.add(notification);
            notification.setCourse(this);
        }
    }

    public void removeNotification(Notification notification) {
        if(this.notifications.contains(notification)) {
            this.notifications.remove(notification);
            notification.setCourse(null);
        }
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;

    }
}
