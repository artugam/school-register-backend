package com.artur.engineer.entities;

import com.artur.engineer.engine.views.SubjectScheduleView;
import com.artur.engineer.engine.views.UserView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Entity
public class SubjectSchedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(SubjectScheduleView.class)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Subject subject;

    @JsonView(SubjectScheduleView.class)
    @OneToMany(mappedBy = "subjectSchedule", cascade = CascadeType.ALL)
    private Collection<SubjectPresence> presences;

    @JsonView(SubjectScheduleView.class)
    private Date start;

    @JsonView(SubjectScheduleView.class)
    private Date end;

    @JsonView(SubjectScheduleView.class)
    @Column(columnDefinition="TEXT")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Collection<SubjectPresence> getPresences() {
        return presences;
    }

    public void setPresences(Collection<SubjectPresence> presences) {
        this.presences = presences;
    }

    public void addPresence(SubjectPresence presence) {
        if(!this.presences.contains(presence)) {
            this.presences.add(presence);
            presence.setSubjectSchedule(this);
        }
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
