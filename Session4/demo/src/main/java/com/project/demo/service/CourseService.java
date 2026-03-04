package com.project.demo.service;

import com.project.demo.dtos.CourseCreateRequestDTO;
import com.project.demo.models.Course;
import com.project.demo.models.enums.CourseStatus;
import com.project.demo.reponses.CourseResponse;
import com.project.demo.reponses.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseCreateRequestDTO CourseCreateRequestDTO);

    // READ ALL
    List<CourseResponse> findAll();

    PageResponse<CourseResponse> findAll(Integer page, Integer size, String sortBy, Sort.Direction direction );

    PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status)
    // READ BY ID
    Course findById(Long id);

    // UPDATE
    Course updateCourse(Long id, CourseCreateRequestDTO courseCreateRequestDTO);

    // DELETE
    void deleteById(Long id);

}
