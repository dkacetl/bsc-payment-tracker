package com.bsc.javatest.parser;

/**
 * Created by dkacetl on 9.3.16.
 */
public class ValidationException extends RuntimeException {

    private String invalidInput;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, String invalidInput) {
        super(message);
        this.invalidInput = invalidInput;
    }

    public ValidationException(String message, Throwable cause, String invalidInput) {
        super(message, cause);
        this.invalidInput = invalidInput;
    }

    public ValidationException(Throwable cause, String invalidInput) {
        super(cause);
        this.invalidInput = invalidInput;
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
            String invalidInput) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.invalidInput = invalidInput;
    }
}
