package com.artur.engineer.payload.course;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class CourseCreate {

    @NotBlank
    private String name;

    @NotBlank
    private String form;

    @NotBlank
    private String degree;

    @NotNull
    private int semesters;

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
        this.form = form;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getSemesters() {
        return semesters;
    }

    public void setSemesters(int semesters) {
        this.semesters = semesters;
    }
}
