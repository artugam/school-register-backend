package com.artur.engineer.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
public class CourseStarted extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Course course;

    private Date startDate = new Date();

    private int semester;


    @ManyToMany(mappedBy = "startedCourses")
    private Collection<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
