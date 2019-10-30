package com.artur.engineer.entities;

import com.artur.engineer.engine.views.CourseView;
import com.artur.engineer.engine.views.CourseWithUserView;
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
    @JsonView({CourseView.class, CourseWithUserView.class})
    private Long id;


    private String name;

    @ManyToOne
    @JoinColumn
    private Course course;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private Collection<User> users = new HashSet<>();


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
        if(!this.users.contains(user)) {
            this.users.add(user);
            user.addGroup(this);
        }
    }

    public void removeUser(User user) {
        if(!this.users.contains(user)) {
            this.users.add(user);
            user.removeGroup(this);
        }
    }
}
