package com.artur.engineer.engine.exceptions;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class ApiException extends Exception {
    public ApiException(String exception) {
        super(exception);
    }

    public final static Integer USER_NOT_FOUND_WITH_EMAIL = 601;
}
