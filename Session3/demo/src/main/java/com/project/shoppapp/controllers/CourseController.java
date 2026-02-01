package com.project.shoppapp.controllers;

import com.project.shoppapp.models.Course;
import com.project.shoppapp.response.ApiResponse;
import com.project.shoppapp.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        Course saved = courseService.createCourse(course);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Tạo course thành công", saved)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Danh sách course", courseService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy course thành công", courseService.findById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody Course course
    ) {
        course.setId(id);
        courseService.updateCourse(course);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Update thành công", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa thành công", null)
        );
    }
}
