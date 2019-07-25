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


    @ManyToMany(mappedBy = "startedCourses")
    private Collection<User> users = new HashSet<>();

}
