package com.project.demo.Controller;

import com.project.demo.DTO.ApiResponse;
import com.project.demo.Entity.Doctor;
import com.project.demo.Service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Bài tập 1: Thiết kế tài nguyên theo Naming Convention
 * - Sử dụng danh từ số nhiều: /doctors
 * - Không dùng động từ trên URL (tránh /getAllDoctors, /deleteDoctor)
 * - Sử dụng đúng HTTP Method: GET, POST, PUT, DELETE
 *
 * Bài tập 6: Sửa lỗi API POST /api/doctor/update-info?id=10
 * - Sai 1: Dùng POST thay vì PUT cho cập nhật → Sửa: PUT
 * - Sai 2: Dùng danh từ số ít "doctor" → Sửa: "doctors" (số nhiều)
 * - Sai 3: Dùng động từ "update-info" trên URL → Sửa: bỏ động từ, dùng /{id}
 * - Sai 4: Truyền ID qua query param ?id=10 → Sửa: dùng path variable /{id}
 * → Đúng: PUT /api/v1/doctors/{id} với body JSON
 */
@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // GET /api/v1/doctors - Lấy danh sách toàn bộ bác sĩ
    @GetMapping
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(ApiResponse.success(doctors));
    }

    // GET /api/v1/doctors/{id} - Lấy thông tin chi tiết một bác sĩ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Doctor>> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(ApiResponse.success(doctor));
    }

    // POST /api/v1/doctors - Thêm mới một bác sĩ
    @PostMapping
    public ResponseEntity<ApiResponse<Doctor>> createDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor created = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(ApiResponse.created(created), HttpStatus.CREATED);
    }

    // PUT /api/v1/doctors/{id} - Cập nhật thông tin bác sĩ
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Doctor>> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody Doctor doctor) {
        Doctor updated = doctorService.updateDoctor(id, doctor);
        return ResponseEntity.ok(ApiResponse.success(updated));
    }

    // DELETE /api/v1/doctors/{id} - Xóa bác sĩ khỏi hệ thống
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa bác sĩ thành công"));
    }
}
