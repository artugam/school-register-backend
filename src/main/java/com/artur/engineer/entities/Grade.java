package com.artur.engineer.entities;

import javax.persistence.*;
import java.util.Arrays;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Entity
public class Grade extends BaseEntity {

    public final static double GRADE_TWO = 2;
    public final static double GRADE_THREE = 3;
    public final static double GRADE_THREE_PLUS = 3.5;
    public final static double GRADE_FOUR = 4;
    public final static double GRADE_FOUR_PLUS = 4.5;
    public final static double GRADE_FIVE = 5;

    public final static double ALLOWED_GRADES[] = {
            GRADE_TWO,
            GRADE_THREE,
            GRADE_THREE_PLUS,
            GRADE_FOUR,
            GRADE_FOUR_PLUS,
            GRADE_FIVE,
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Subject subject;

    private double grade;

    private String description;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (!Arrays.asList(ALLOWED_GRADES).contains(grade)) {
            return;
        }
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
