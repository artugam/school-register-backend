package com.artur.engineer.entities;

import com.artur.engineer.engine.views.user.UserViews;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(UserViews.Normal.class)
    private Long id;

    @JsonView(UserViews.Normal.class)
    private String firstName;

    @JsonView(UserViews.Normal.class)
    private String lastName;

    @JsonView(UserViews.Normal.class)
    @Column(unique = true)
    private String email;

    private String password;

    @JsonView(UserViews.Normal.class)
    private boolean enabled;

    @JsonView(UserViews.Normal.class)
    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @JsonManagedReference
    @JsonView(UserViews.Normal.class)
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Grade> grades;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "user_started_course",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "started_course_id", referencedColumnName = "id"))
    private Collection<CourseStarted> startedCourses;


    @JsonManagedReference
    @ManyToMany(mappedBy = "teachers")
    private Collection<SubjectSchedule> teachSubjects;

    @JsonManagedReference
    @ManyToMany(mappedBy = "students")
    private Collection<SubjectSchedule> learnSubjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<CourseStarted> getStartedCourses() {
        return startedCourses;
    }

    public void setStartedCourses(Collection<CourseStarted> startedCourses) {
        this.startedCourses = startedCourses;
    }

    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    public Collection<SubjectSchedule> getTeachSubjects() {
        return teachSubjects;
    }

    public void setTeachSubjects(Collection<SubjectSchedule> teachSubjects) {
        this.teachSubjects = teachSubjects;
    }

    public Collection<SubjectSchedule> getLearnSubjects() {
        return learnSubjects;
    }

    public void setLearnSubjects(Collection<SubjectSchedule> learnSubjects) {
        this.learnSubjects = learnSubjects;
    }
}
