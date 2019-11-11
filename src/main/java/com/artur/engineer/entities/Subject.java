package com.artur.engineer.entities;

import com.artur.engineer.engine.views.SubjectView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

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
    @JsonView({SubjectView.class})
    private Long id;

    @JsonView({SubjectView.class})
    private String name;

    @JsonView({SubjectView.class})
    private double hours;

    @JsonView({SubjectView.class})
    private String type;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Collection<SubjectSchedule> subjectSchedule;

    @ManyToOne
    @JoinColumn
    @JsonView({SubjectView.class})
    private CourseGroup group;



    @JsonView({SubjectView.class})
    @ManyToMany
    @JoinTable(
            name = "subject_teachers",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id", referencedColumnName = "id"))
    private Collection<User> teachers;

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
}
