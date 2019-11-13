package com.artur.engineer.payload.notification;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class NotificationCreatePayload {

    @NotBlank
    protected String description;

    @NotNull
    protected Long courseId;

    protected Long groupId;

    protected Long subjectId;

    public String getDescription() {
        return description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public Long getGroupId() {
        return groupId;
    }
}
