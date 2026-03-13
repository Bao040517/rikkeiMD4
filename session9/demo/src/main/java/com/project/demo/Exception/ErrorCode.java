package com.project.demo.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {

    VALIDATION_ERROR(1001, 400, "Validation failed"),
    DEPARTMENT_ALREADY_EXISTS(1002, 409, "Department already exists"),
    RESOURCE_NOT_FOUND(1003, 404, "Resource not found"),
    INTERNAL_SERVER_ERROR(1004, 500, "Internal server error"),
    DUPLICATE_RESOURCE_EXCEPTION(1005,409, "Duplicate resource");

    private final int code;
    private final int status;
    private final String message;

    ErrorCode(int code, int status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
