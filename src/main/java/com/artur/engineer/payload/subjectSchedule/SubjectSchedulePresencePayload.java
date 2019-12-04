package com.artur.engineer.payload.subjectSchedule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class SubjectSchedulePresencePayload {

    @NotNull
    private Long userId;

    @NotBlank
    private String value;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
