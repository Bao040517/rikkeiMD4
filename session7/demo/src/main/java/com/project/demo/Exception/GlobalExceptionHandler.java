package com.project.demo.Exception;

import com.project.demo.DTOs.Responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ApiResponse<Map<String, String>> apiResponse = ApiResponse.<Map<String, String>>builder()
                .status("FAILED")
                .message("Validation error")
                .data(errors)
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(SalaryRangeException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>>
    handleIllegalArgumentException(SalaryRangeException ex) {
        Map<String,String> error = new HashMap<>();

        error.put("error", ex.getMessage());

        ApiResponse<Map<String,String>> apiResponse = ApiResponse.<Map<String, String>>builder()
                .status("Fail")
                .message("Has Error")
                .data(error)
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }
}