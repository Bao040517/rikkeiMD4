package com.project.demo.exceptions;

import com.project.demo.reponses.ApiResponse;
import com.project.demo.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<String>builder()
                        .code("404")
                        .message(e.getMessage())
                        .data(null)
                        .build()
        );
    }
}
