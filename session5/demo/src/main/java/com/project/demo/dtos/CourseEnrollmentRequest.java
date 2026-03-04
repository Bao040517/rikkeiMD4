package com.project.demo.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseEnrollmentRequest {
    private Long studentId;
}
