package com.artur.engineer.payload.subjectSchedule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class SubjectScheduleCreate {

    @NotNull
    private Long subjectId;

    @NotNull
    private Date start;

    @NotNull
    private Date end;

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Long getSubjectId() {
        return subjectId;
    }
}
