package com.artur.engineer.payload.course;

import java.util.Collection;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class CourseConfigurationResponse {

    private Collection forms;

    private Collection degrees;

    public Collection getForms() {
        return forms;
    }

    public void setForms(Collection forms) {
        this.forms = forms;
    }

    public Collection getDegrees() {
        return degrees;
    }

    public void setDegrees(Collection degrees) {
        this.degrees = degrees;
    }
}
