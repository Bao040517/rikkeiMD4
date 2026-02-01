package com.project.shoppapp.DTOs;

import com.project.shoppapp.models.Course;
import lombok.Data;

@Data
public class EnrollCourseRequest {
    private Long courseId;
    private String studentName;
}
