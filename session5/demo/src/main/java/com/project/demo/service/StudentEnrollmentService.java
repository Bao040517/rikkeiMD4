package com.project.demo.service;

import com.project.demo.models.StudentEnrollment;

import java.util.List;

public interface StudentEnrollmentService {
    StudentEnrollment enrollStudentToCourse(Long studentId, Long courseId);
    List<StudentEnrollment> findAll();
    StudentEnrollment findById(Long id);
    void deleteStudentEnrollment(Long studentId, Long courseId);
    void deleteById(Long id);
}
