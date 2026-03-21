package com.project.demo.Controller;

import com.project.demo.DTO.ApiResponse;
import com.project.demo.Entity.Prescription;
import com.project.demo.Service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Bài tập 3: Xử lý tài nguyên phức tạp (Complex Resources)
 * Quy tắc: /resource/{id}/sub-resource
 * Bệnh nhân (parent) → Đơn thuốc (child)
 */
@RestController
@RequestMapping("/api/v1/patients/{patientId}/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    // GET /api/v1/patients/{patientId}/prescriptions - Danh sách đơn thuốc của bệnh nhân
    @GetMapping
    public ResponseEntity<ApiResponse<List<Prescription>>> getPrescriptionsByPatient(
            @PathVariable Long patientId) {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPatientId(patientId);
        return ResponseEntity.ok(ApiResponse.success(prescriptions));
    }

    // GET /api/v1/patients/{patientId}/prescriptions/{id} - Chi tiết đơn thuốc
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Prescription>> getPrescriptionById(
            @PathVariable Long patientId,
            @PathVariable Long id) {
        Prescription prescription = prescriptionService.getPrescriptionById(patientId, id);
        return ResponseEntity.ok(ApiResponse.success(prescription));
    }

    // POST /api/v1/patients/{patientId}/prescriptions - Thêm đơn thuốc cho bệnh nhân
    @PostMapping
    public ResponseEntity<ApiResponse<Prescription>> createPrescription(
            @PathVariable Long patientId,
            @Valid @RequestBody Prescription prescription) {
        Prescription created = prescriptionService.createPrescription(patientId, prescription);
        return new ResponseEntity<>(ApiResponse.created(created), HttpStatus.CREATED);
    }
}
