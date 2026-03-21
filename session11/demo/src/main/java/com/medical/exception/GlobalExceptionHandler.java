package com.medical.exception;

import com.medical.dto.ApiResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Bài 4: Logging trong Exception Handling tập trung
 * Ghi lại đầy đủ Stack Trace mà không lộ thông tin ra phía Client
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseError> handleAllExceptions(Exception e) {
        // Ghi log ERROR với đầy đủ Stack Trace
        log.error("Lỗi hệ thống xảy ra: ", e);

        // Trả về thông tin lỗi an toàn cho Client (không lộ stack trace)
        ApiResponseError errorResponse = new ApiResponseError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau.",
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
