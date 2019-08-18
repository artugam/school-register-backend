package com.artur.engineer.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;
/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
