package com.project.demo.dtos;

import com.project.demo.models.enums.CourseStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class CourseCreateRequestDTO {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}
