package com.artur.engineer.entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "form", "degree"})
})
public class Course extends BaseEntity {

    public static final String FORM_STATIC = "Stacjonarne";
    public static final String FORM_UNSTATIC = "Niestacjonarne";

    public static final String ALLOWED_FORMS[] = {
            FORM_STATIC,
            FORM_UNSTATIC
    };

    public static final String DEGREE_I = "I Stopnia";
    public static final String DEGREE_II = "II Stopnia";
    public static final String DEGREE_III = "III Stopnia";

    public static final String ALLOWED_DEGREES[] = {
            DEGREE_I,
            DEGREE_II,
            DEGREE_III
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String form;

    private String degree;

    private int semesters;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Collection<CourseStarted> startedCourses;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        if (!Arrays.asList(ALLOWED_FORMS).contains(form)) {
            return;
        }
        this.form = form;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        if (!Arrays.asList(ALLOWED_DEGREES).contains(degree)) {
            return;
        }
        this.degree = degree;
    }

    public Collection<CourseStarted> getStartedCourses() {
        return startedCourses;
    }

    public void setStartedCourses(Collection<CourseStarted> startedCourses) {
        this.startedCourses = startedCourses;
    }

    public int getSemesters() {
        return semesters;
    }

    public void setSemesters(int semesters) {
        this.semesters = semesters;
    }
}
