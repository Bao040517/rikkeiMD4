package com.project.demo.Service.impl;

import com.project.demo.Entity.Patient;
import com.project.demo.Entity.Prescription;
import com.project.demo.Exception.ResourceNotFoundException;
import com.project.demo.Repository.PrescriptionRepository;
import com.project.demo.Service.PatientService;
import com.project.demo.Service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PatientService patientService;

    @Override
    public List<Prescription> getPrescriptionsByPatientId(Long patientId) {
        patientService.getPatientById(patientId);
        return prescriptionRepository.findByPatientId(patientId);
    }

    @Override
    public Prescription getPrescriptionById(Long patientId, Long prescriptionId) {
        patientService.getPatientById(patientId);
        return prescriptionRepository.findByIdAndPatientId(prescriptionId, patientId)
                .orElseThrow(() -> new ResourceNotFoundException("đơn thuốc", prescriptionId));
    }

    @Override
    public Prescription createPrescription(Long patientId, Prescription prescription) {
        Patient patient = patientService.getPatientById(patientId);
        prescription.setPatient(patient);
        return prescriptionRepository.save(prescription);
    }
}
