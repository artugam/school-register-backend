package com.artur.engineer.engine.validator;

import com.artur.engineer.entities.User;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class EmailExistsValidator implements
        ConstraintValidator<EmailExistsConstraint, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void initialize(EmailExistsConstraint emailExistsConstraint) {

    }
}
