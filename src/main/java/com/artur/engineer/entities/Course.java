package com.artur.engineer.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "form", "degree"})
})
public class Course extends BaseEntity{

    public static final String FORM_STATIC = "Stacjonarne";
    public static final String FORM_UNSTATIC = "Niestacjonarne";

    public static final String DEGREE_I = "I Stopnia";
    public static final String DEGREE_II = "II Stopnia";
    public static final String DEGREE_III = "III Stopnia";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String form;

    private String degree;

    private int termsAmount;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Collection<CourseStarted> startedCourses;

}
