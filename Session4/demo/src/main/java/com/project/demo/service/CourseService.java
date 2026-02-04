package com.project.demo.service;

import com.project.demo.dtos.CourseCreateRequestDTO;
import com.project.demo.models.Course;
import com.project.demo.reponses.CourseResponse;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseCreateRequestDTO CourseCreateRequestDTO);

    // READ ALL
    List<CourseResponse> findAll();

    // READ BY ID
    Course findById(Long id);

    // UPDATE
    Course updateCourse(Long id, CourseCreateRequestDTO courseCreateRequestDTO);

    // DELETE
    void deleteById(Long id);

}
