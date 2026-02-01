package com.project.shoppapp.service.Impl;

import com.project.shoppapp.DTOs.EnrollCourseRequest;
import com.project.shoppapp.DTOs.EnrollmentDetail;
import com.project.shoppapp.DTOs.InstructorDetail;
import com.project.shoppapp.exceptions.NotFoundException;
import com.project.shoppapp.exceptions.isNullException;
import com.project.shoppapp.models.Course;
import com.project.shoppapp.models.Enrollment;
import com.project.shoppapp.models.Instructor;
import com.project.shoppapp.models.enums.CourseStatus;
import com.project.shoppapp.repository.CourseRepository;
import com.project.shoppapp.repository.EnrollmentRepository;
import com.project.shoppapp.repository.InstructorRepository;
import com.project.shoppapp.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No enrollment found with id: " + id));
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
        Enrollment updatedEnrollment = enrollmentRepository.findById(enrollment.getId()).orElseThrow(() -> new IllegalArgumentException(
                                "No enrollment found with id: " + enrollment.getId()
                        )
                );
        updatedEnrollment.setStudentName(enrollment.getStudentName());
        updatedEnrollment.setCourseId(enrollment.getCourseId());
        enrollmentRepository.save(updatedEnrollment);
    }

    @Override
    public void deleteById(Long id) {
        Enrollment deleteenrollment = enrollmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No enrollment found with id: " + id));
        enrollmentRepository.delete(deleteenrollment);
    }

    @Override
    public EnrollmentDetail enrollCourse(EnrollCourseRequest enrollCourseRequest) {
        Course course = courseRepository.findById(enrollCourseRequest.getCourseId()).orElseThrow(() -> new NotFoundException("Không tìm thấy"));
        if(course.getStatus() != CourseStatus.ACTIVE) {
            throw new isNullException("Khoá học chưa được phát hành");
        }
        Instructor instructor = instructorRepository.findById(
                course.getInstructorId()).orElseThrow(() -> new NotFoundException(
                        "Không tìm thấy instructor với id: " + course.getInstructorId()));
        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(course.getId());
        enrollment.setStudentName(enrollCourseRequest.getStudentName());
        enrollmentRepository.save(enrollment);
         EnrollmentDetail enrollmentDetail = new EnrollmentDetail();
         enrollmentDetail.setCourse(course);
         enrollmentDetail.setStudentName(enrollCourseRequest.getStudentName());
        return enrollmentDetail;
    }
}
