package com.project.demo.reponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final String code;
    private final String message;
    private final T data;
}
