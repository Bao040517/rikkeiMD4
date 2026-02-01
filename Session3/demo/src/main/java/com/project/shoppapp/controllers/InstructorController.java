package com.project.shoppapp.controllers;

import com.project.shoppapp.models.Instructor;
import com.project.shoppapp.response.ApiResponse;
import com.project.shoppapp.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> create(@RequestBody Instructor instructor) {
        Instructor saved = instructorService.createInstructor(instructor);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Tạo instructor thành công", saved)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Danh sách instructor", instructorService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy instructor thành công", instructorService.findById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody Instructor instructor
    ) {
        instructor.setId(id);
        instructorService.updateInstructor(instructor);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Update instructor thành công", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        instructorService.deleteById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa instructor thành công", null)
        );
    }
}
