package com.project.shoppapp.controllers.seeder;

import com.project.shoppapp.models.*;
import com.project.shoppapp.repository.*;
import com.project.shoppapp.repository.CourseRepository;
import com.project.shoppapp.repository.EnrollmentRepository;
import com.project.shoppapp.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Random;

@RestController
@RequestMapping("/seeder")
@RequiredArgsConstructor
public class SeederController {
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final InstructorRepository instructorRepository;

    @GetMapping("/run")
    public String seedData() {
        Faker faker = new Faker(new Locale("vi"));
        Random random = new Random();

        // ===== Instructor (20) =====
        for (int i = 0; i < 20; i++) {
            Instructor instructor = new Instructor();
            instructor.setName(faker.name().fullName());
            instructor.setEmail(faker.internet().emailAddress());

            instructorRepository.save(instructor);
        }

        // ===== Course (20) =====
        for (int i = 0; i < 20; i++) {
            Course course = new Course();
            course.setStudentName(faker.name().fullName());
            course.setCourseId((long) random.nextInt(10) + 1);

            courseRepository.save(course);
        }

        // ===== Enrollment (20) =====
        for (int i = 0; i < 20; i++) {
            Enrollment enrollment = new Enrollment();
            enrollment.setTitle("Khoá học " + faker.educator().course());
            enrollment.setStatus(faker.bool().bool());
            enrollment.setInstructorId((long) random.nextInt(20) + 1);

            enrollmentRepository.save(enrollment);
        }

        return "✅ Seed dữ liệu thành công (60 bản ghi)";
    }
}
