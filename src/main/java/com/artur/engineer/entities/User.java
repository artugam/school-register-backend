package com.artur.engineer.entities;

import com.artur.engineer.engine.views.UserView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(UserView.class)
    private Long id;

    @JsonView(UserView.class)
    private String firstName;

    @JsonView(UserView.class)
    private String lastName;

    @JsonView({UserView.class, UserView.class})
    @Column(unique = true)
    private String email;

    private String password;

    @JsonView(UserView.class)
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @JsonView(UserView.class)
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Grade> grades;


    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"))
    private Collection<Course> courses;

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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        if(!this.courses.contains(course)) {
            this.courses.add(course);
            course.addUser(this);
        }
    }

    public void removeCourse(Course course) {
        if(this.courses.contains(course)) {
            this.courses.remove(course);
            course.removeUser(this);
        }
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
