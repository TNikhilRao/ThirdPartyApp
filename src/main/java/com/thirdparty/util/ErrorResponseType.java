package com.thirdparty.util;

import org.springframework.http.HttpStatus;

import com.google.common.base.MoreObjects;
import com.thirdparty.exception.ExceptionType;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public enum ErrorResponseType {
    INTERNAL_SERVER_ERROR(ExceptionType.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, 1),
    SECURITY_ERROR(ExceptionType.SECURITY_ERROR, HttpStatus.UNAUTHORIZED, 2),
    BUSINESS_RULE_VIOLATION_ERROR(ExceptionType.BUSINESS_RULE_VIOLATION_ERROR, HttpStatus.FORBIDDEN, 3),
    VALIDATION_ERROR(ExceptionType.VALIDATION_ERROR, HttpStatus.BAD_REQUEST, 4),
    INVALID_JSON_ERROR(ExceptionType.INVALID_JSON_ERROR, HttpStatus.BAD_REQUEST, 5),
    NOT_FOUND_ERROR(ExceptionType.NOT_FOUND_ERROR, HttpStatus.NOT_FOUND, 6);

    private String category;
    private HttpStatus status;
    private Integer code;

    private ErrorResponseType(String category, HttpStatus status, Integer code) {
        this.category = category;
        this.status = status;
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                        .add("category", category)
                        .add("status", status)
                        .add("code", code)
                        .toString();
    }

}
