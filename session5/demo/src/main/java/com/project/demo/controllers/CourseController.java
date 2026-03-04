package com.project.demo.controllers;

import com.project.demo.dtos.CourseCreateRequestDTO;
import com.project.demo.models.Course;
import com.project.demo.models.enums.CourseStatus;
import com.project.demo.reponses.ApiResponse;
import com.project.demo.reponses.CourseResponse;
import com.project.demo.reponses.PageResponse;
import com.project.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
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
        public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getAllCourses(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(required = false) String sortBy,
                        @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                        @RequestParam(defaultValue = "ACTIVE") CourseStatus status) {
                return ResponseEntity.ok(
                                ApiResponse.<PageResponse<CourseResponse>>builder()
                                                .code("200")
                                                .message("Get courses successfully")
                                                .data(courseService.findAll(page, size, sortBy, direction))
                                                .build());
        }

        @GetMapping("/status")
        public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getAllCoursesByStatus(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(required = false) String sortBy,
                        @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                        @RequestParam(defaultValue = "ACTIVE") CourseStatus status) {
                return ResponseEntity.ok(
                                ApiResponse.<PageResponse<CourseResponse>>builder()
                                                .code("200")
                                                .message("Get courses successfully")
                                                .data(courseService.getPagedCoursesByStatus(page, size, sortBy,
                                                                direction, status))
                                                .build());
        }
}
