package com.artur.engineer.engine.validator;

import com.artur.engineer.entities.User;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Optional;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class EmailExistsValidator implements
        ConstraintValidator<EmailExistsConstraint, String> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest request;
//
//    @Autowired
//    HttpServletResponse response;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);


        if(pathVariables.size() > 0 && !pathVariables.get("userId").equals("")) {
            Long value = Long.parseLong(pathVariables.get("userId").toString());
            return !userRepository.findByEmailAndIdNot(email, value).isPresent();
        }

        return !userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void initialize(EmailExistsConstraint emailExistsConstraint) {

    }
}
