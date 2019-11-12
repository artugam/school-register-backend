package com.artur.engineer.payload.presence;

import com.artur.engineer.engine.views.SubjectPresenceView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class SubjectPresenceConfigurationOptions {

    @JsonView({SubjectPresenceView.class})
    private String[] types;

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] subjectTypes) {
        this.types = subjectTypes;
    }
}
