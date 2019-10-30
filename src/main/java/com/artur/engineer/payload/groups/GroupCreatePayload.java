package com.artur.engineer.payload.groups;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class GroupCreatePayload {

    @NotBlank
    protected String name;

    @NotNull
    protected Long courseId;

    public String getName() {
        return name;
    }

    public Long getCourseId() {
        return courseId;
    }
}
