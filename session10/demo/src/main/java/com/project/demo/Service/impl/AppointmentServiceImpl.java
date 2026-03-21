package com.project.demo.Service.impl;

import com.project.demo.Entity.Appointment;
import com.project.demo.Entity.Doctor;
import com.project.demo.Exception.ResourceNotFoundException;
import com.project.demo.Repository.AppointmentRepository;
import com.project.demo.Service.AppointmentService;
import com.project.demo.Service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;

    @Override
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        doctorService.getDoctorById(doctorId);
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public Appointment getAppointmentById(Long doctorId, Long appointmentId) {
        doctorService.getDoctorById(doctorId);
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("lịch hẹn", appointmentId));
    }

    @Override
    public Appointment createAppointment(Long doctorId, Appointment appointment) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        appointment.setDoctor(doctor);
        return appointmentRepository.save(appointment);
    }
}
