package com.artur.engineer.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean enabled;

    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Grade> grades;

    @ManyToMany
    @JoinTable(
            name = "user_started_course",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "started_course_id", referencedColumnName = "id"))
    private Collection<CourseStarted> startedCourses;

    @ManyToMany(mappedBy = "teachers")
    private Collection<SubjectSchedule> teachSubjects;

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
}
