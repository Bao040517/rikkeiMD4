package com.project.demo.controllers;

import com.project.demo.dtos.CourseCreateRequestDTO;
import com.project.demo.models.Course;
import com.project.demo.reponses.ApiResponse;
import com.project.demo.reponses.CourseResponse;
import com.project.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createCourse(@RequestBody CourseCreateRequestDTO course) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<String>builder()
                        .message("Course created successfully")
                        .code("201")
                        .data(null)
                        .build());
    }
    @PutMapping
    public ResponseEntity<ApiResponse<String>> updateCourse(@PathVariable Long id,
                               @RequestBody CourseCreateRequestDTO course) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .message("Course updated successfully")
                .code("200")
                .data(null)
                .build());
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses() {
        return ResponseEntity.ok(
                ApiResponse.<List<CourseResponse>>builder()
                        .code("200")
                        .message("Get courses successfully")
                        .data(courseService.findAll())
                        .build()
        );
    }
}
