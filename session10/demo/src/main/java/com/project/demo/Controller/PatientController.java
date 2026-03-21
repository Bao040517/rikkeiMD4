package com.project.demo.Controller;

import com.project.demo.DTO.ApiResponse;
import com.project.demo.Entity.Patient;
import com.project.demo.Service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PatientController - Quản lý bệnh nhân
 * Bài tập 2: Kiểm tra HTTP Status Codes
 * - TH1: POST /patients thiếu tên → 400 Bad Request (Validation)
 * - TH2: GET /patients/999 không tồn tại → 404 Not Found
 * - TH4: POST /patients tuổi = -5 → 400 Bad Request (Validation @Min)
 */
@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // GET /api/v1/patients - Danh sách bệnh nhân
    @GetMapping
    public ResponseEntity<ApiResponse<List<Patient>>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(ApiResponse.success(patients));
    }

    // GET /api/v1/patients/{id} - Chi tiết bệnh nhân
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(ApiResponse.success(patient));
    }

    // POST /api/v1/patients - Thêm bệnh nhân
    @PostMapping
    public ResponseEntity<ApiResponse<Patient>> createPatient(@Valid @RequestBody Patient patient) {
        Patient created = patientService.createPatient(patient);
        return new ResponseEntity<>(ApiResponse.created(created), HttpStatus.CREATED);
    }

    // PUT /api/v1/patients/{id} - Cập nhật bệnh nhân
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody Patient patient) {
        Patient updated = patientService.updatePatient(id, patient);
        return ResponseEntity.ok(ApiResponse.success(updated));
    }

    // DELETE /api/v1/patients/{id} - Xóa bệnh nhân
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa bệnh nhân thành công"));
    }
}
