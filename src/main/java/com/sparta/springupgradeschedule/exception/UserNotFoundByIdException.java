package com.sparta.springupgradeschedule.exception;

public class UserNotFoundByIdException extends RuntimeException {
    public UserNotFoundByIdException() {}

    public UserNotFoundByIdException(String message) {
        super(message);
    }
}
