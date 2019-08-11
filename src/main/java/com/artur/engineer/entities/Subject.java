package com.artur.engineer.entities;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Subject {

    public final static String GRADE_TYPE_PASSED = "Zaliczenie";
    public final static String GRADE_TYPE_PASSED_WITH_GRADE = "Zaliczenie na ocene";
    public final static String GRADE_TYPE_EXAM = "Egzamin";

    public final static String ALLOWED_GRADES_TYPES[] = {
            Subject.GRADE_TYPE_EXAM,
            Subject.GRADE_TYPE_PASSED_WITH_GRADE,
            Subject.GRADE_TYPE_PASSED,
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private double hours;

    private String gradeType;

    private int ects;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Collection<SubjectSchedule> subjectSchedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        if(!Arrays.asList(ALLOWED_GRADES_TYPES).contains(gradeType)) {
            return;
        }
        this.gradeType = gradeType;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public Collection<SubjectSchedule> getSubjectSchedule() {
        return subjectSchedule;
    }

    public void setSubjectSchedule(Collection<SubjectSchedule> subjectSchedule) {
        this.subjectSchedule = subjectSchedule;
    }
}
