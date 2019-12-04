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

    private String description;

    private Integer amount;

    public final static String FREQUENCY_ONE_WEEK = "oneWeek";
    public final static String FREQUENCY_TWO_WEEKS = "twoWeeks";

    public final static String[] ALLOWED_FREQUENCIES = {
            FREQUENCY_ONE_WEEK,
            FREQUENCY_TWO_WEEKS
    };

    private String frequency;

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getFrequency() {
        return frequency;
    }
}
