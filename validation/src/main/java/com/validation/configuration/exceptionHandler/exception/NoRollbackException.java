package com.validation.configuration.exceptionHandler.exception;

public class NoRollbackException extends Exception {
    public NoRollbackException() {}

    public NoRollbackException(String message) {
        super(message);
    }
}
