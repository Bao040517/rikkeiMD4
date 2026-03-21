package com.medical.controller;

import com.medical.entity.Patient;
import com.medical.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    /**
     * Bài 1: Ghi log INFO tên bệnh nhân, WARN nếu tuổi > 120
     */
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        log.info("Tiếp nhận bệnh nhân: {}", patient.getName());

        // Ghi log WARN nếu tuổi quá cao
        if (patient.getAge() != null && patient.getAge() > 120) {
            log.warn("Cảnh báo: Tuổi bệnh nhân {} quá cao: {} tuổi!", patient.getName(), patient.getAge());
        }

        Patient savedPatient = patientService.addPatient(patient);
        log.info("Đã lưu bệnh nhân {} thành công với ID: {}", savedPatient.getName(), savedPatient.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    /**
     * Lấy danh sách tất cả bệnh nhân
     */
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        log.info("Yêu cầu lấy danh sách tất cả bệnh nhân");
        List<Patient> patients = patientService.getAllPatients();
        log.info("Trả về {} bệnh nhân", patients.size());
        return ResponseEntity.ok(patients);
    }

    /**
     * Tìm bệnh nhân theo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        log.info("Tìm kiếm bệnh nhân với ID: {}", id);
        return patientService.findById(id)
                .map(patient -> {
                    log.info("Đã tìm thấy bệnh nhân: {}", patient.getName());
                    return ResponseEntity.ok(patient);
                })
                .orElseGet(() -> {
                    log.warn("Không tìm thấy bệnh nhân với ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Bài 4: Endpoint test gây lỗi (chia cho 0) để kiểm tra Exception Handling
     */
    @GetMapping("/test-error")
    public ResponseEntity<String> testError() {
        log.info("Gọi endpoint test-error");
        int result = 10 / 0; // Gây lỗi ArithmeticException
        return ResponseEntity.ok("Kết quả: " + result);
    }
}
