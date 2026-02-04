package com.project.demo.dtos;

import lombok.Builder;
import lombok.Data;

@Data
public class CourseUpdateRequest {
    private Long id;
    private CourseUpdateRequest courseUpdateRequest;
}
