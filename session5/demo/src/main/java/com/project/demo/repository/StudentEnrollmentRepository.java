package com.project.demo.repository;

import com.project.demo.models.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment,Long> {
    Optional<StudentEnrollment> findByStudent_IdAndCourse_Id(
        Long studentId,
        Long courseId);
}
