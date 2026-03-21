package com.project.demo.Service;

import com.project.demo.Entity.Prescription;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> getPrescriptionsByPatientId(Long patientId);
    Prescription getPrescriptionById(Long patientId, Long prescriptionId);
    Prescription createPrescription(Long patientId, Prescription prescription);
}
