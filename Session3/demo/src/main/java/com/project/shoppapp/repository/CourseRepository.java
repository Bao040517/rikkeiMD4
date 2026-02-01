package com.project.shoppapp.repository;

import com.project.shoppapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    boolean findByInstructorId(Long instructorId);
    List<Course> findCourseByInstructorId(Long instructorId);
}
