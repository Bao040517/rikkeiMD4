package com.project.shoppapp.service.Impl;

import com.project.shoppapp.DTOs.InstructorDetail;
import com.project.shoppapp.exceptions.NotFoundException;
import com.project.shoppapp.models.Course;
import com.project.shoppapp.models.Instructor;
import com.project.shoppapp.models.enums.CourseStatus;
import com.project.shoppapp.repository.CourseRepository;
import com.project.shoppapp.repository.InstructorRepository;
import com.project.shoppapp.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Override
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No instructor found with id: " + id));
    }

    @Override
    public void updateInstructor(Instructor instructor) {
        Instructor updated = instructorRepository.findById(instructor.getId())
                .orElseThrow(() -> new IllegalArgumentException("No instructor found with id: " + instructor.getId()));

        updated.setName(instructor.getName());
        updated.setEmail(instructor.getEmail());

        instructorRepository.save(updated);
    }

    @Override
    public void deleteById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No instructor found with id: " + id));

        instructorRepository.delete(instructor);
    }

    @Override
    public List<InstructorDetail> findAllInstructors() {
        List<InstructorDetail> result = new ArrayList<>();
        List<Instructor> instructors = instructorRepository.findAll();
        for (Instructor instructor : instructors) {
            List<Course> courses = courseRepository.findCourseByInstructorId(instructor.getId());
            List<Course> activeCourses = new ArrayList<>();
            for (Course course : courses) {
                if (course.getStatus() == CourseStatus.ACTIVE) {
                    activeCourses.add(course);
                }
            }
            if (activeCourses.isEmpty()) {
                continue;
            }
            InstructorDetail instructorDetail = InstructorDetail.builder()
                    .instructors(instructor)
                    .courses(activeCourses)
                    .build();
            result.add(instructorDetail);
        }
        return result;
    }

}
