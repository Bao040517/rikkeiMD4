package com.project.shoppapp.DTOs;

import com.project.shoppapp.models.Course;
import com.project.shoppapp.models.Instructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class InstructorDetail {
    Instructor instructors;
    List<Course> courses;
}
