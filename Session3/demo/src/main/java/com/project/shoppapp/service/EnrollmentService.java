package com.project.shoppapp.service;

import com.project.shoppapp.DTOs.EnrollCourseRequest;
import com.project.shoppapp.DTOs.EnrollmentDetail;
import com.project.shoppapp.DTOs.InstructorDetail;
import com.project.shoppapp.models.Course;
import com.project.shoppapp.models.Enrollment;
import com.project.shoppapp.models.Instructor;

import java.util.List;

public interface EnrollmentService {
    Enrollment createEnrollment(Enrollment enrollment);
    List<Enrollment> findAll();
    Enrollment findById(Long id);
    void updateEnrollment(Enrollment enrollment);
    void deleteById(Long id);
    EnrollmentDetail enrollCourse(EnrollCourseRequest enrollCourseRequest);
}
