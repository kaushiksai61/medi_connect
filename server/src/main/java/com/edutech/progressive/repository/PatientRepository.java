package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByPatientId(int patientId);
    Patient findByEmail(String email);
}