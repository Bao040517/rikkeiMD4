package com.project.demo.Controller;

import com.project.demo.DTO.ApiResponse;
import com.project.demo.Entity.Appointment;
import com.project.demo.Service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Bài tập 3: Xử lý tài nguyên phức tạp (Complex Resources)
 * Quy tắc: /resource/{id}/sub-resource
 * Bác sĩ (parent) → Lịch hẹn (child)
 */
@RestController
@RequestMapping("/api/v1/doctors/{doctorId}/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    // GET /api/v1/doctors/{doctorId}/appointments - Lấy tất cả lịch hẹn của bác sĩ
    @GetMapping
    public ResponseEntity<ApiResponse<List<Appointment>>> getAppointmentsByDoctor(
            @PathVariable Long doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(ApiResponse.success(appointments));
    }

    // GET /api/v1/doctors/{doctorId}/appointments/{id} - Lấy chi tiết lịch hẹn
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Appointment>> getAppointmentById(
            @PathVariable Long doctorId,
            @PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(doctorId, id);
        return ResponseEntity.ok(ApiResponse.success(appointment));
    }

    // POST /api/v1/doctors/{doctorId}/appointments - Tạo lịch hẹn cho bác sĩ
    @PostMapping
    public ResponseEntity<ApiResponse<Appointment>> createAppointment(
            @PathVariable Long doctorId,
            @Valid @RequestBody Appointment appointment) {
        Appointment created = appointmentService.createAppointment(doctorId, appointment);
        return new ResponseEntity<>(ApiResponse.created(created), HttpStatus.CREATED);
    }
}
