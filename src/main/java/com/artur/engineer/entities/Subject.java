package com.artur.engineer.entities;

import com.artur.engineer.engine.views.SubjectScheduleFullView;
import com.artur.engineer.engine.views.SubjectView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Subject extends BaseEntity {

    public final static String SUBJECT_TYPE_LAB = "Laboratorium";
    public final static String SUBJECT_TYPE_LECTURE = "Wykład";
    public final static String SUBJECT_TYPE_EXERCISES = "Ćwiczenia";

    public final static String ALLOWED_SUBJECT_TYPES[] = {
            Subject.SUBJECT_TYPE_LAB,
            Subject.SUBJECT_TYPE_LECTURE,
            Subject.SUBJECT_TYPE_EXERCISES,
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({SubjectView.class, SubjectScheduleFullView.class})
    private Long id;

    @JsonView({SubjectView.class, SubjectScheduleFullView.class})
    private String name;

    @JsonView({SubjectView.class})
    private double hours;

    @JsonView({SubjectView.class, SubjectScheduleFullView.class})
    private String type;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<SubjectSchedule> subjectSchedule;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Grade> grades;

    @ManyToOne
    @JoinColumn
    @JsonView({SubjectView.class, SubjectScheduleFullView.class})
    private CourseGroup group;

    @JsonView({SubjectView.class, SubjectScheduleFullView.class})
    @ManyToMany
    @JoinTable(
            name = "subject_teachers",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id", referencedColumnName = "id"))
    private Collection<User> teachers;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Notification> notifications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }


    public Collection<SubjectSchedule> getSubjectSchedule() {
        return subjectSchedule;
    }

    public void setSubjectSchedule(Collection<SubjectSchedule> subjectSchedule) {
        this.subjectSchedule = subjectSchedule;
    }

    public CourseGroup getGroup() {
        return group;
    }

    public void setGroup(CourseGroup group) {
        this.group = group;
    }

    public Collection<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<User> teachers) {
        this.teachers = teachers;
    }

    public String getType() {
        return type;
    }

    public void setType(String subjectType) {
        this.type = subjectType;
    }

    public void addTeacher(User u) {

        if (!this.teachers.contains(u)) {
            this.teachers.add(u);
            u.addTeachSubject(this);
        }
    }

    public void removeTeacher(User u) {
        if (this.teachers.contains(u)) {
            this.teachers.remove(u);
            u.removeTeachSubject(this);
        }
    }

    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    public void addNotification(Notification notification) {
        if(!this.notifications.contains(notification)) {
            this.notifications.add(notification);
            notification.setSubject(this);
        }
    }

    public void removeNotification(Notification notification) {
        if(this.notifications.contains(notification)) {
            this.notifications.remove(notification);
            notification.setSubject(null);
        }
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;

    }
}
