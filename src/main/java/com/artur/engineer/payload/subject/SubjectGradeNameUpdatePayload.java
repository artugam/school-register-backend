package com.artur.engineer.payload.subject;

import javax.validation.constraints.NotBlank;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class SubjectGradeNameUpdatePayload {

    @NotBlank
    private String newDescription;

    @NotBlank
    private String oldDescription;

    public String getNewDescription() {
        return newDescription;
    }

    public String getOldDescription() {
        return oldDescription;
    }
}
