package com.artur.engineer.payload.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class UserCreate {

    @NotBlank
    @Email
    protected String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    protected String password;

    @NotBlank
    protected String passwordConfirm;

}
