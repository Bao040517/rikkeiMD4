package com.project.shoppapp.DTOs;

import com.project.shoppapp.models.Course;
import lombok.Data;

@Data
public class EnrollmentDetail {
    private Long id;
    String studentName;
    Course course;
}
