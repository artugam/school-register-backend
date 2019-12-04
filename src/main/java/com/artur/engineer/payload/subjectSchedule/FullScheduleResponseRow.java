package com.artur.engineer.payload.subjectSchedule;

import com.artur.engineer.engine.views.PagedView;
import com.artur.engineer.entities.SubjectPresence;
import com.artur.engineer.entities.SubjectSchedule;
import com.artur.engineer.entities.User;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class FullScheduleResponseRow {

    @JsonView({PagedView.class})
    public User user;

    @JsonView({PagedView.class})
    public Collection<SubjectPresence> presences = new ArrayList<>();
}
