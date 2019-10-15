package com.artur.engineer.payload;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
