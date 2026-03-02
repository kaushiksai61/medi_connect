package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.exception.PatientAlreadyExistsException;
import com.edutech.progressive.exception.PatientNotFoundException;
import com.edutech.progressive.repository.PatientRepository;
import com.edutech.progressive.service.PatientService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
@Transactional
public class PatientServiceImplJpa implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImplJpa(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() throws Exception {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(int patientId) throws Exception {
        Patient p = patientRepository.findByPatientId(patientId);
        if (p == null) {
            throw new PatientNotFoundException("Patient not found: " + patientId);
        }
        return p;
    }

    @Override
    public Integer addPatient(Patient patient) throws Exception {
        if (patient == null || patient.getEmail() == null) {
            throw new PatientAlreadyExistsException("Invalid patient email");
        }
        Patient existing = patientRepository.findByEmail(patient.getEmail());
        if (existing != null) {
            throw new PatientAlreadyExistsException("Patient already exists with email: " + patient.getEmail());
        }
        return patientRepository.save(patient).getPatientId();
    }

    @Override
    public void updatePatient(Patient patient) throws Exception {
        if (patient == null) return;
        Patient persisted = patientRepository.findByPatientId(patient.getPatientId());
        if (persisted == null) {
            throw new PatientNotFoundException("Patient not found: " + patient.getPatientId());
        }
        if (patient.getEmail() != null) {
            Patient byEmail = patientRepository.findByEmail(patient.getEmail());
            if (byEmail != null && byEmail.getPatientId() != patient.getPatientId()) {
                throw new PatientAlreadyExistsException("Patient already exists with email: " + patient.getEmail());
            }
        }
        patientRepository.save(patient);
    }

    @Override
    public void deletePatient(int patientId) throws Exception {
        Patient persisted = patientRepository.findByPatientId(patientId);
        if (persisted == null) {
            throw new PatientNotFoundException("Patient not found: " + patientId);
        }
        patientRepository.delete(persisted);
    }

    @Override
    public List<Patient> getAllPatientSortedByName() throws Exception {
        return patientRepository.findAll(Sort.by(Sort.Direction.ASC, "fullName"));
    }
}