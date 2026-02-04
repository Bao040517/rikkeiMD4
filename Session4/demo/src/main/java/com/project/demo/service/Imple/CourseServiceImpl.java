package com.project.demo.service.Imple;

import com.project.demo.dtos.CourseCreateRequestDTO;
import com.project.demo.exceptions.NotFoundException;
import com.project.demo.mapper.CourseMapper;
import com.project.demo.models.Course;
import com.project.demo.models.Instructor;
import com.project.demo.reponses.CourseInstructorResponse;
import com.project.demo.reponses.CourseResponse;
import com.project.demo.repository.CourseRepository;
import com.project.demo.repository.InstructorRepository;
import com.project.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CourseMapper courseMapper;

    @Override
    public Course createCourse(CourseCreateRequestDTO courseCreateRequestDTO) {
//        Long instructorId = courseCreateRequestDTO.getInstructor().getId();
//        Instructor instructor = instructorRepository.findById(instructorId)
//                .orElseThrow(() -> new NotFoundException("Instructor not found with id: " + instructorId));
        Instructor instructor = instructorRepository.findById(courseCreateRequestDTO.getInstructorId()).orElseThrow(() -> new NotFoundException("Instructor not found"));
        return courseRepository.save(courseMapper.toEntity(courseCreateRequestDTO, instructor));
    }
    @Override
    public List<CourseResponse> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseResponse(
                        course.getId(),
                        course.getTitle(),
                        course.getStatus(),
                        new CourseInstructorResponse(
                                course.getInstructor().getId(),
                                course.getInstructor().getName()
                        )
                ))
                .toList();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Course not found with id: " + id)
                );
    }
    @Override
    public Course updateCourse(Long id, CourseCreateRequestDTO courseCreateRequestDTO) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
        existingCourse.setTitle(courseCreateRequestDTO.getTitle());
        existingCourse.setStatus(courseCreateRequestDTO.getStatus());
        if (courseCreateRequestDTO.getInstructorId() != null) {
            Instructor instructor = instructorRepository.findById(courseCreateRequestDTO.getInstructorId()).orElseThrow(() -> new NotFoundException("Instructor not found"));
            existingCourse.setInstructor(instructor);
        }
        return courseRepository.save(existingCourse);
    }
    @Override
    public void deleteById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
        courseRepository.delete(course);
    }
}
