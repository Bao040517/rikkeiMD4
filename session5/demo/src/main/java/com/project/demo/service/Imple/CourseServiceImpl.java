package com.project.demo.service.Imple;

import com.project.demo.dtos.CourseCreateRequestDTO;
import com.project.demo.exceptions.NotFoundException;
import com.project.demo.mapper.CourseMapper;
import com.project.demo.models.Course;
import com.project.demo.models.Instructor;
import com.project.demo.models.enums.CourseStatus;
import com.project.demo.reponses.CourseInstructorResponse;
import com.project.demo.reponses.CourseResponse;
import com.project.demo.reponses.PageResponse;
import com.project.demo.repository.CourseRepository;
import com.project.demo.repository.InstructorRepository;
import com.project.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CourseMapper courseMapper;
    private final RestClient.Builder builder;

    @Override
    public Course createCourse(CourseCreateRequestDTO courseCreateRequestDTO) {
        // Long instructorId = courseCreateRequestDTO.getInstructor().getId();
        // Instructor instructor = instructorRepository.findById(instructorId)
        // .orElseThrow(() -> new NotFoundException("Instructor not found with id: " +
        // instructorId));
        Instructor instructor = instructorRepository.findById(courseCreateRequestDTO.getInstructorId())
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
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
                                course.getInstructor().getName())))
                .toList();
    }

    @Override
    public PageResponse<CourseResponse> findAll(Integer page, Integer size, String sortBy, Sort.Direction direction) {
        Sort.Direction sortDirection = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<CourseResponse> coursePage = courseRepository.findAll(pageable).map(course -> CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .status(course.getStatus())
                .build());
        return PageResponse.<CourseResponse>builder()
                .items(coursePage.getContent())
                .page(coursePage.getNumber())
                .size(coursePage.getSize())
                .totalItems((int) coursePage.getTotalElements())
                .totalPages(coursePage.getTotalPages())
                .isLast(coursePage.isLast())
                .build();
    }

    @Override
    public PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy,
            Sort.Direction direction, CourseStatus status) {
        if (direction == null) {
            direction = Sort.Direction.ASC;
        }
        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<CourseResponse> courseResponses = courseRepository.findAllByStatus(status, pageable)
                .map(course -> CourseResponse.builder()
                        .id(course.getId())
                        .title(course.getTitle())
                        .status(course.getStatus())
                        .build());

        return PageResponse.<CourseResponse>builder()
                .items(courseResponses.getContent())
                .page(courseResponses.getNumber())
                .size(courseResponses.getSize())
                .totalItems((int) courseResponses.getTotalElements())
                .totalPages(courseResponses.getTotalPages())
                .isLast(courseResponses.isLast())
                .build();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
    }

    @Override
    public Course updateCourse(Long id, CourseCreateRequestDTO courseCreateRequestDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
        existingCourse.setTitle(courseCreateRequestDTO.getTitle());
        existingCourse.setStatus(courseCreateRequestDTO.getStatus());
        if (courseCreateRequestDTO.getInstructorId() != null) {
            Instructor instructor = instructorRepository.findById(courseCreateRequestDTO.getInstructorId())
                    .orElseThrow(() -> new NotFoundException("Instructor not found"));
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
