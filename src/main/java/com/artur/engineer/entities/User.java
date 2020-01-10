package com.artur.engineer.entities;

import com.artur.engineer.engine.views.SubjectScheduleFullView;
import com.artur.engineer.engine.views.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(UserView.class)
    private Long id;

    @JsonView({UserView.class, SubjectScheduleFullView.class})
    private String firstName;

    @JsonView({UserView.class, SubjectScheduleFullView.class})
    private String lastName;

    @JsonView({UserView.class, UserView.class, SubjectScheduleFullView.class})
    @Column(unique = true)
    private String email;

    private String password;

    @JsonView({UserView.class, SubjectScheduleFullView.class})
    private String uniqueNumber;

    private String passwordResetToken;

    @JsonView(UserView.class)
    private boolean enabled;

    @JsonView(UserView.class)
    private String lockReason;

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
    private Collection<Course> courses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_courseGroup",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "course_group_id", referencedColumnName = "id"))
    private Collection<CourseGroup> groups;

    @OneToMany(mappedBy = "foreman", cascade = CascadeType.ALL)
    private Collection<Course> foremanCourses;

    @ManyToMany(mappedBy = "teachers")
    private Collection<Subject> teachSubjects;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Collection<Notification> notifications;

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
    public void addRole(Role role) {
        if(!this.roles.contains(role)) {
            this.roles.add(role);
        }
    }

    public void removeRole(Role role) {
        if(this.roles.contains(role)) {
            this.roles.remove(role);
        }
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {

        if (!this.courses.contains(course)) {
            this.courses.add(course);
            course.addUser(this);
        }
    }

    public void removeCourse(Course course) {
        if (this.courses.contains(course)) {
            this.courses.remove(course);
            course.removeUser(this);
        }
    }

    public Collection<Grade> getGrades() {
        return grades;
    }

    public Grade getGrade(Subject subject, String description) {
        for (Grade grade : this.grades) {
            if (
                    null != grade &&
                    null != grade.getSubject() &&
                    grade.getSubject().equals(subject) &&
                    null != grade.getDescription() &&
                    grade.getDescription().equals(description)
            ) {
                return grade;
            }
        }
        return null;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    public Collection<Subject> getTeachSubjects() {
        return teachSubjects;
    }

    public void setTeachSubjects(Collection<Subject> teachSubjects) {
        this.teachSubjects = teachSubjects;
    }

    public Collection<Course> getForemanCourses() {
        return foremanCourses;
    }

    public void setForemanCourses(Collection<Course> foremanCourses) {
        this.foremanCourses = foremanCourses;
    }

    public void addForemanCourse(Course course) {
        if (!this.foremanCourses.contains(course)) {
            this.foremanCourses.add(course);
            course.setForeman(this);
        }
    }

    public void removeForemanCourse(Course course) {
        if (this.foremanCourses.contains(course)) {
            this.foremanCourses.remove(course);
            course.setForeman(null);
        }
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
            group.addUser(this);
        }
    }

    public void removeGroup(CourseGroup group) {
        if (this.groups.contains(group)) {
            this.groups.remove(group);
            group.removeUser(this);
        }
    }

    public void addTeachSubject(Subject subject) {
        if (!this.teachSubjects.contains(subject)) {
            this.teachSubjects.add(subject);
            subject.addTeacher(this);
        }
    }

    public void removeTeachSubject(Subject subject) {
        if (this.teachSubjects.contains(subject)) {
            this.teachSubjects.remove(subject);
            subject.removeTeacher(this);
        }
    }

    public void addNotification(Notification notification) {
        if (!this.notifications.contains(notification)) {
            this.notifications.add(notification);
            notification.setCreator(this);
        }
    }

    public void removeNotification(Notification notification) {
        if (this.notifications.contains(notification)) {
            this.notifications.remove(notification);
            notification.setCreator(null);
        }
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;

    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }
}
