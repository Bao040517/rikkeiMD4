package com.medical.service;

import com.medical.entity.Patient;
import com.medical.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient addPatient(Patient patient) {
        log.trace("Bắt đầu thêm bệnh nhân: {}", patient);
        Patient savedPatient = patientRepository.save(patient);
        log.trace("Đã lưu bệnh nhân thành công với ID: {}", savedPatient.getId());
        return savedPatient;
    }

    public List<Patient> getAllPatients() {
        log.trace("Bắt đầu lấy danh sách tất cả bệnh nhân");
        List<Patient> patients = patientRepository.findAll();
        log.trace("Tìm thấy {} bệnh nhân", patients.size());
        return patients;
    }

    public Optional<Patient> findById(Long id) {
        log.debug("Tìm kiếm bệnh nhân với ID: {}", id);
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            log.debug("Đã tìm thấy bệnh nhân: {}", patient.get().getName());
        } else {
            log.debug("Không tìm thấy bệnh nhân với ID: {}", id);
        }
        return patient;
    }
}
