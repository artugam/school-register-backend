package com.artur.engineer.payload.subject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class SubjectCreate {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private double hours;

    @NotBlank
    private String type;

    @NotNull
    private Long groupId;


    private List<Long> teachersIds;

    public String getName() {
        return name;
    }

    public double getHours() {
        return hours;
    }

    public String getType() {
        return type;
    }

    public Long getGroupId() {
        return groupId;
    }

    public List<Long> getTeachersIds() {
        return teachersIds;
    }
}
