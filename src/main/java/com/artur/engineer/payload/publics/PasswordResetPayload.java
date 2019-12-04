package com.artur.engineer.payload.publics;

import javax.validation.constraints.NotBlank;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class PasswordResetPayload {

    @NotBlank
    private String email;

    public String getEmail() {
        return email;
    }
}
