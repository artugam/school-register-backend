package com.artur.engineer.payload.publics;

import javax.validation.constraints.NotBlank;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class PasswordSetPayload {

    @NotBlank
    private String password;

    @NotBlank
    private String token;

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}
