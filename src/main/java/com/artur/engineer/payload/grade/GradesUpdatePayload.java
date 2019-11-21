package com.artur.engineer.payload.grade;

import javax.validation.constraints.NotNull;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class GradesUpdatePayload {

    @NotNull
    private Long id;

    private Double grade;

    public Long getId() {
        return id;
    }

    public Double getGrade() {
        return grade;
    }
}
