package com.artur.engineer.payload.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class UserCreateWithPassword extends UserCreate{

    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$")
    protected String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
