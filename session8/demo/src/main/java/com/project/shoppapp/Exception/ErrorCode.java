package com.project.shoppapp.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    FILE_UPLOAD_FAILED("E001", "Upload file failed"),
    BOOK_NOT_FOUND("E002", "Book not found"),
    INTERNAL_SERVER_ERROR("E500", "Internal server error"),
    RESOURCE_NOT_FOUND("E404", "Not found"),
    BOOK_ALREADY_RETURNED("E401", "Book already returned");
    private final String code;
    private final String message;

}
