package com.artur.engineer.entities;

import com.artur.engineer.engine.views.CourseView;
import com.artur.engineer.engine.views.CourseWithUserView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "form", "degree", "startDate"})
})
public class CourseGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({CourseView.class, CourseWithUserView.class})
    private Long id;

    @ManyToOne
    @JoinColumn
    private Course course;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private Collection<User> users = new HashSet<>();

}
