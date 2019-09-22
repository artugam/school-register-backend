package com.artur.engineer.payload.user;

import com.artur.engineer.engine.validator.EmailExistsConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
//@PasswordEqualsConstraint.List({
//    @PasswordEqualsConstraint(
//            field = "password",
//            fieldMatch = "passwordConfirm",
//            message = "Passwords do not match!"
//    )
//})
public class UserCreate {

    @NotBlank
    @Email
    @EmailExistsConstraint
    protected String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$")
    protected String password;

    @NotBlank
    protected String firstName;

    @NotBlank
    protected String lastName;

    @NotNull
    protected Long role;


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


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }
}
