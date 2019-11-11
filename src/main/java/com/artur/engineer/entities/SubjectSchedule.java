package com.artur.engineer.entities;

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
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Subject subject;

    @OneToMany(mappedBy = "subjectSchedule", cascade = CascadeType.ALL)
    private Collection<Grade> grades;

    private Date start;
    private Date end;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
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

}
