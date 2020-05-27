package com.test.dto.converter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.response.ResponseErrorDto;

public class ResponseValidationErrorDto extends ResponseErrorDto {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> validationErrors;

    public ResponseValidationErrorDto() {
    }

    public ResponseValidationErrorDto(String code, String message, List<String> validationErrors) {
        super(code, message);
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
