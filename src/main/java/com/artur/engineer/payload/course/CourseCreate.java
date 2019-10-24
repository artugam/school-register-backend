package com.artur.engineer.payload.course;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


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

    @NotNull
    private Date startDate;

    private int currentSemester;

    public String getName() {
        return name;
    }

    public String getForm() {
        return form;
    }

    public String getDegree() {
        return degree;
    }

    public int getSemesters() {
        return semesters;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }
}
