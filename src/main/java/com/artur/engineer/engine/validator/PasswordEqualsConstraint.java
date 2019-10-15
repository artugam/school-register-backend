package com.artur.engineer.engine.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Documented
@Constraint(validatedBy = PasswordEqualsValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordEqualsConstraint {

    String message() default "Passwords are equals!!";

    String field();

    String fieldMatch();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        PasswordEqualsConstraint[] value();
    }
}
