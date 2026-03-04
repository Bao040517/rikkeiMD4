package com.project.demo.mapper;

import com.project.demo.dtos.CourseCreateRequestDTO;
import com.project.demo.models.Course;
import com.project.demo.models.Instructor;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toEntity(CourseCreateRequestDTO dto, Instructor instructor) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setStatus(dto.getStatus());
        course.setInstructor(instructor);
        return course;
    }
}
