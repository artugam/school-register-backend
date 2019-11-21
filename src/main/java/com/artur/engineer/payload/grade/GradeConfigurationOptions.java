package com.artur.engineer.payload.grade;

import com.artur.engineer.engine.views.GradeView;
import com.artur.engineer.engine.views.SubjectPresenceView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class GradeConfigurationOptions {

    @JsonView({GradeView.class})
    private Double[] options;

    public Double[] getOptions() {
        return options;
    }

    public void setOptions(Double[] subjectTypes) {
        this.options = subjectTypes;
    }
}
