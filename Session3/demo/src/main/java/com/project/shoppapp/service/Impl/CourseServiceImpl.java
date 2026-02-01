package com.project.shoppapp.service.Impl;

import com.project.shoppapp.models.Course;
import com.project.shoppapp.repository.CourseRepository;
import com.project.shoppapp.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;


    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No course found with id: " + id));
    }

    @Override
    public void updateCourse(Course course) {
            Course updatedCourse = courseRepository.findById(course.getId()).orElseThrow(() -> new IllegalArgumentException("No course found with id: " + course.getId()));
            updatedCourse.setCourseId(course.getCourseId());
            updatedCourse.setStudentName(course.getStudentName());
            courseRepository.save(updatedCourse);
    }

    @Override
    public void deleteById(Long id) {
        Course deleteCourse = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No course found with id: " + id));
        courseRepository.delete(deleteCourse);
    }
}
