package com.project.demo.repository;

import com.project.demo.models.Course;
import com.project.demo.models.enums.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE (:status IS NULL OR c.status = :status)")
    Page<Course> findAllByStatus(@Param("status") CourseStatus status,Pageable pageable);
}
