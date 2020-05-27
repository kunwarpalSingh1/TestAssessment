package com.test.exception;



import org.springframework.http.HttpStatus;

public enum InternalServerExceptionType {

    FILE_IO_ERROR("file.io.error", "I/O error occurred during file upload.", HttpStatus.NOT_FOUND),
    AUTH_NOT_FOUND("auth.not.found", "There is no account corresponding to the data provided.", HttpStatus.UNAUTHORIZED),
    AUTH_CREDENTIALS_EXPIRED("auth.credentials.expired", "Your credentials has expired and must be changed.", HttpStatus.UNAUTHORIZED),
    AUTH_ACCOUNT_LOCKED("auth.account.locked", "Your account has been locked out because you have reached the maximum number of invalid logon attempts. Please try again in %d minutes.", HttpStatus.UNAUTHORIZED),
    AUTH_BAD_CREDENTIALS("auth.bad.credentials", "Invalid credentials.", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    InternalServerExceptionType(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
