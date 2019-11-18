package com.artur.engineer.payload.subjectSchedule.grades;

import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.payload.subjectSchedule.FullScheduleResponseRow;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class FullGradesResponse {

    @JsonView({PagedView.class})
    public List<String> sections = new ArrayList<>();

    @JsonView({PagedView.class})
    public Collection<FullScheduleResponseRow> rows = new ArrayList<>();

}
