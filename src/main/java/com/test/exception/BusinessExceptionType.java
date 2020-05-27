package com.test.exception;

import org.springframework.http.HttpStatus;

public enum BusinessExceptionType {

    //TODO Business Exceptions should not include exceptions that sre not related to business login (like io exception, another kind of Excpetion should be created like "ServerException"
    NOT_FOUND("not.found", "The specified entity can not be found.", HttpStatus.NOT_FOUND.value()),
    ACCESS_DENIED("access.denied", "Forbidden.", HttpStatus.FORBIDDEN.value()),
    DOCUMENT_NOT_FOUND("not.found", "The specified document is not found.", HttpStatus.NOT_FOUND.value()),
    DOCUMENT_NOT_VISIBLE("document.not.visible", "The specified document is not found.", HttpStatus.NOT_FOUND.value()), //todo maybe we shouldn't expose to ui that document is not visible and use not found instead.
    FILE_ALREADY_EXISTS("file.already.exists", "The file is already existing.", HttpStatus.UNPROCESSABLE_ENTITY.value()),
    FILE_IO_ERROR("file.io.error", "I/O error occurred during file upload.", HttpStatus.NOT_FOUND.value()),
    FILE_SAVE_INTERNAL_ERROR("file.save.internal.error", "Failed to save file due to some internal error.", HttpStatus.NOT_FOUND.value()),
    FAILED_TO_DELETE_FILE("failed.delete.file", "Failed to delete the specified file from storage.", HttpStatus.NOT_FOUND.value()),
    USER_WITH_EMAIL_ALREADY_EXISTS("user.already.exists", "User with given email already exists in the system.", HttpStatus.UNPROCESSABLE_ENTITY.value()),
    USER_WITH_SECURE_EMAIL_ALREADY_EXISTS("user.already.exists.secure.email", "User with given secure email already exists in the system.", HttpStatus.UNPROCESSABLE_ENTITY.value()),
    PASSWORD_UPDATE_INCORRECT_CONTACT_STATUS("password.update.incorrect.contact.status","Contact status is incorrect to create/update password.", HttpStatus.FORBIDDEN.value()),
    MISSING_REQUIRED_FIELDS("missing.required.fields","Required fields are empty.", HttpStatus.UNPROCESSABLE_ENTITY.value()),
    PASSWORD_COMPLEXITY_VALIDATION_FAILURE("password.complexity.validation.failure","The password you entered doesn't meet complexity requirements.", HttpStatus.UNPROCESSABLE_ENTITY.value()),
    PASSWORD_HISTORY_VALIDATION_FAILURE("password.history.validation.failure","In order to secure your account, please create a unique password you have not used before.", HttpStatus.UNPROCESSABLE_ENTITY.value());

    private final String code;
    private final String message;
    private final int httpStatus;

    BusinessExceptionType(String code, String message, int httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public int httpStatus() {
        return httpStatus;
    }

}
