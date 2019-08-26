package com.artur.engineer.engine.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class PasswordEqualsValidator implements
        ConstraintValidator<PasswordEqualsConstraint, Object> {

    private String password;
    private String passwordConfirm;

    public void initialize(PasswordEqualsConstraint constraintAnnotation) {
        this.password = constraintAnnotation.field();
        this.passwordConfirm = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(password);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(passwordConfirm);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        }
        return false;
    }
}