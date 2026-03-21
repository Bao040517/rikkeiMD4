package com.project.demo.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private String status;
    private int code;
    private T data;
    private String message;

    // Success response với data
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .status("success")
                .code(200)
                .data(data)
                .build();
    }

    // Success response với message
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .status("success")
                .code(200)
                .message(message)
                .build();
    }

    // Created response (201)
    public static <T> ApiResponse<T> created(T data) {
        return ApiResponse.<T>builder()
                .status("success")
                .code(201)
                .data(data)
                .build();
    }

    // Error response
    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .status("error")
                .code(code)
                .message(message)
                .build();
    }
}
