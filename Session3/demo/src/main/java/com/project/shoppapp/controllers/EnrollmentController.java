package com.project.shoppapp.controllers;

import com.project.shoppapp.models.Enrollment;
import com.project.shoppapp.response.ApiResponse;
import com.project.shoppapp.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> create(@RequestBody Enrollment enrollment) {
        Enrollment saved = enrollmentService.createEnrollment(enrollment);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Tạo enrollment thành công", saved)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Danh sách enrollment", enrollmentService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy enrollment thành công", enrollmentService.findById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody Enrollment enrollment
    ) {
        enrollment.setId(id);
        enrollmentService.updateEnrollment(enrollment);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Update enrollment thành công", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        enrollmentService.deleteById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa enrollment thành công", null)
        );
    }
}
