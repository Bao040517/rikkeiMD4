package com.project.demo.Service;

import com.project.demo.Entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointmentsByDoctorId(Long doctorId);
    Appointment getAppointmentById(Long doctorId, Long appointmentId);
    Appointment createAppointment(Long doctorId, Appointment appointment);
}
