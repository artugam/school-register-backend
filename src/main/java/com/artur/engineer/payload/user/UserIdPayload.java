package com.artur.engineer.payload.user;

import com.artur.engineer.engine.validator.EmailExistsConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class UserIdPayload {

    @NotBlank
    @NotNull
    protected Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
