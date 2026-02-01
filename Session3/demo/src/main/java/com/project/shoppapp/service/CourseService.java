package com.project.shoppapp.service;

import com.project.shoppapp.models.Course;
import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    List<Course> findAll();
    Course findById(Long id);
    void updateCourse(Course course);
    void deleteById(Long id);

}
