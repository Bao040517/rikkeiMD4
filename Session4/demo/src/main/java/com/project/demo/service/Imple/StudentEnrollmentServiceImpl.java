package com.project.demo.service.Imple;

import com.project.demo.exceptions.NotFoundException;
import com.project.demo.models.Course;
import com.project.demo.models.Student;
import com.project.demo.models.StudentEnrollment;
import com.project.demo.models.enums.CourseStatus;
import com.project.demo.repository.CourseRepository;
import com.project.demo.repository.StudentEnrollmentRepository;
import com.project.demo.repository.StudentRepository;
import com.project.demo.service.StudentEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

    private final StudentEnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public StudentEnrollment enrollStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new NotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId).
                orElseThrow(() ->
                        new NotFoundException("Course not found with id: " + courseId));
        if (course.getStatus() != CourseStatus.ACTIVE) {
            throw new IllegalStateException("Course is not ACTIVE");
        }
        List<StudentEnrollment> valid = course.getStudentEnrollments();
        Boolean isValid = valid.stream().anyMatch(studentEnrollment -> studentEnrollment.getStudent().getId().equals(student.getId()));
        if (isValid) {
            throw new IllegalStateException("Student already enrolled in this course !");
        }
        else{
            StudentEnrollment enrollment = new StudentEnrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setEnrolledAt(LocalDateTime.now());
            return enrollmentRepository.save(enrollment);
        }
    }
    @Override
    public List<StudentEnrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public StudentEnrollment findById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Enrollment not found with id: " + id)
                );
    }

    @Override
    public void deleteStudentEnrollment(Long studentId, Long courseId) {

        StudentEnrollment enrollment = enrollmentRepository
                .findByStudent_IdAndCourse_Id(studentId, courseId)
                .orElseThrow(() ->
                        new NotFoundException("Student chưa đăng ký course này")
                );

        enrollmentRepository.delete(enrollment);
    }

    @Override
    public void deleteById(Long id) {
        StudentEnrollment enrollment = findById(id);
        enrollmentRepository.delete(enrollment);
    }
}
