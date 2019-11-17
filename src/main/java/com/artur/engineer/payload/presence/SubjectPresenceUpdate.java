package com.artur.engineer.payload.presence;

import com.artur.engineer.engine.views.SubjectPresenceView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class SubjectPresenceUpdate {

    @NotNull
    private Long schedulePresenceId;

    private String status;

    public Long getSchedulePresenceId() {
        return schedulePresenceId;
    }

    public String getStatus() {
        return status;
    }
}
