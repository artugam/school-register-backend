package com.artur.engineer.payload.subject;

import com.artur.engineer.engine.views.UserView;
import com.artur.engineer.entities.User;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class SubjectConfigurationOptions {

    @JsonView({UserView.class})
    private Collection<User> teachers;

    @JsonView({UserView.class})
    private String[] types;

    public Collection<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<User> teachers) {
        this.teachers = teachers;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] subjectTypes) {
        this.types = subjectTypes;
    }
}
