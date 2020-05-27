package com.test.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.dto.converter.ResponseValidationErrorDto;
import com.test.exception.ApplicationException;
import com.test.exception.BusinessException;
import com.test.exception.BusinessExceptionType;
import com.test.exception.InternalServerException;
import com.test.exception.ValidationException;


@XmlRootElement
public class Response<T> {
    @XmlTransient
    public static Integer SUCCESS_STATUS_CODE = HttpStatus.OK.value();

    @XmlTransient
    public static Integer INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();

    @XmlTransient
    public static Integer NOT_FOUND = HttpStatus.NOT_FOUND.value();

    public static <T> Response<T> successResponse() {
        Response<T> response = new Response<>(SUCCESS_STATUS_CODE, true, null, null);
        return response;
    }

    public static <T> Response<T> successResponse(T data) {
        Response<T> response = new Response<>(SUCCESS_STATUS_CODE, true, null, data);
        return response;
    }

    public static <T> Response<T> successResponse(Body<T> responseBody) {
        Response<T> response = new Response<>(SUCCESS_STATUS_CODE, true, responseBody);
        return response;
    }

    public static <T> Response<List<T>> pageResponse(Page<T> pageable) {
        return Response.pagedResponse(pageable.getContent(), pageable.getTotalElements());
    }

    public static <T> Response<List<T>> pagedResponse(Page<T> pageable) {
        return Response.pagedResponse(pageable.getContent(), pageable.getTotalElements());
    }

    public static <T> Response<T> pagedResponse(T data, Long totalCount) {
        Response<T> response = new Response<>(SUCCESS_STATUS_CODE, true, null, data, totalCount);
        return response;
    }

    @Deprecated
   public static <T> Response<T> errorResponse(String error, int statusCode) {
        Response<T> response = new Response<>(statusCode, false,
                new BusinessException(statusCode == NOT_FOUND ? BusinessException.NOT_FOUND_CODE
                        : BusinessException.INTERNAL_SERVER_ERROR_CODE, error),
                null);
        return response;
    }

   public static Response errorResponse(BusinessException e) {
        Response response = new Response(e.getHttpStatus(), false, e, null);
        return response;
    }

    public static Response errorResponse(InternalServerException e) {
        Response response = new Response(e.getHttpStatus().value(), false, e, null);
        return response;
    }

    @Deprecated
    public static <T> Response<T> errorResponse(BusinessExceptionType errorType) {
        Response<T> response = new Response<>(errorType.httpStatus(), false, new BusinessException(errorType), null);
        return response;
    }

    public Response() {
    }

    public Response(int statusCode, Boolean success, ApplicationException error, T data) {
        this.statusCode = statusCode;
        body = new Body<>(success, error, data);
    }

    public Response(int statusCode, Boolean success, ApplicationException error, T data, Long totalCount) {
        this.statusCode = statusCode;
        body = new Body<>(totalCount, success, error, data);
    }

    public Response(int statusCode, Boolean success, Body<T> body) {
        this.statusCode = statusCode;
        body.setSuccess(success);
        this.body = body;
    }

    private Body<T> body;
    private Integer statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Body<T> {
        private final Long totalCount;
        private Boolean success;
        private ResponseErrorDto error;
        private T data;

        public Body(T data) {
            this.totalCount = null;
            this.success = true;
            this.error = null;
            this.data = data;
        }

        public Body(Long totalCount, Boolean success, ApplicationException error, T data) {
            super();
            this.totalCount = totalCount;
            this.success = success;
            this.error = error == null ? null : new ResponseErrorDto(error.getCode(), error.getMessage());
            this.data = data;
        }

//        public Body(BusinessException error) {
//            this.totalCount = null;
//            this.success = false;
//            this.error = error == null ? null : new ResponseErrorDto(error.getCode(), error.getMessage());
//            this.data = null;
//        }

        public Body(Boolean success, ApplicationException error, T data) {
            this.totalCount = null;
            this.success = success;
            if (error == null) {
                this.error = null;
            } else if (error instanceof ValidationException) {
                this.error = new ResponseValidationErrorDto(error.getCode(), error.getMessage(),
                        ((ValidationException) error).getValidationErrors());
            } else {
                this.error = new ResponseErrorDto(error.getCode(), error.getMessage());
            }
            this.data = data;
        }

        public Long getTotalCount() {
            return totalCount;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public ResponseErrorDto getError() {
            return error;
        }

        public void setError(ResponseErrorDto error) {
            this.error = error;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public Body<T> getBody() {
        return body;
    }

    public void setBody(Body<T> body) {
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
