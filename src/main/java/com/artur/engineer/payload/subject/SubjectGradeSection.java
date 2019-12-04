package com.artur.engineer.payload.subject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class SubjectGradeSection {

    @NotBlank
    private String description;

    public String getDescription() {
        return description;
    }

}
