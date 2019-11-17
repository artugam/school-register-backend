package com.artur.engineer.payload.subjectSchedule;

import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.entities.SubjectSchedule;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class FullScheduleResponse {

    @JsonView({PagedView.class})
    public Collection<SubjectSchedule> schedules = new ArrayList<>();

    @JsonView({PagedView.class})
    public Collection<FullScheduleResponseRow> rows = new ArrayList<>();

}
