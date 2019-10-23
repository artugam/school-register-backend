package com.artur.engineer.engine.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Documented
@Constraint(validatedBy = EmailExistsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailExistsConstraint {
    String message() default "UserIdPayload with provided email exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
