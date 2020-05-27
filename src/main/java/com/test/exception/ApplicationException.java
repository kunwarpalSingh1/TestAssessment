package com.test.exception;


public class ApplicationException extends RuntimeException {

    protected String code;

    public ApplicationException(String message, String code) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }
}
