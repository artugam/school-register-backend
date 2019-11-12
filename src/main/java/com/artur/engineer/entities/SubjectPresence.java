package com.artur.engineer.entities;

import javax.persistence.*;
import java.util.Arrays;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Entity
public class SubjectPresence extends BaseEntity{

    public final static String STATUS_PRESENCE_TRUE = "Obecny";
    public final static String STATUS_PRESENCE_LATE = "Spóźniony";
    public final static String STATUS_PRESENCE_JUSTIFIED = "Usprawiedliwiony";
    public final static String STATUS_PRESENCE_FALSE = "Nieobecny";

    public final static String ALLOWED_STATUS_PRESENCE[] = {
            STATUS_PRESENCE_TRUE,
            STATUS_PRESENCE_LATE,
            STATUS_PRESENCE_JUSTIFIED,
            STATUS_PRESENCE_FALSE
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private SubjectSchedule subjectSchedule;

    @ManyToOne
    @JoinColumn
    private User user;

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
