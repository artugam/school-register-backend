package com.artur.engineer.entities;

import com.artur.engineer.engine.views.SubjectPresenceView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Arrays;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Entity
public class SubjectPresence extends BaseEntity{

    public final static String STATUS_PRESENCE_NONE = "";
    public final static String STATUS_PRESENCE_TRUE = "Obecny";
    public final static String STATUS_PRESENCE_LATE = "Spóźniony";
    public final static String STATUS_PRESENCE_JUSTIFIED = "Usprawiedliwiony";
    public final static String STATUS_PRESENCE_FALSE = "Nieobecny";


    public final static String ALLOWED_STATUS_PRESENCE[] = {
            STATUS_PRESENCE_NONE,
            STATUS_PRESENCE_TRUE,
            STATUS_PRESENCE_LATE,
            STATUS_PRESENCE_JUSTIFIED,
            STATUS_PRESENCE_FALSE
    };

    @Id
    @JsonView(SubjectPresenceView.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private SubjectSchedule subjectSchedule;

    @ManyToOne
    @JoinColumn
    @JsonView(SubjectPresenceView.class)
    private User user;

    @JsonView(SubjectPresenceView.class)
    private String presenceStatus;

    public Long getId() {
        return id;
    }

    public SubjectSchedule getSubjectSchedule() {
        return subjectSchedule;
    }

    public void setSubjectSchedule(SubjectSchedule subjectSchedule) {
        this.subjectSchedule = subjectSchedule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPresenceStatus() {
        return presenceStatus;
    }

    public void setPresenceStatus(String presenceStatus) {
        if (!Arrays.asList(ALLOWED_STATUS_PRESENCE).contains(presenceStatus)) {
            return;
        }
        this.presenceStatus = presenceStatus;
    }


}
