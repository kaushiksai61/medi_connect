package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Appointment;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Appointment findByAppointmentId(int appointmentId);

    List<Appointment> findByClinic_ClinicId(int clinicId);

    List<Appointment> findByPatient_PatientId(int patientId);

    List<Appointment> findByStatus(String status);

    @Modifying
    @Transactional
    @Query("delete from Appointment a where a.clinic.doctor.doctorId = :doctorId")
    void deleteByDoctorId(@Param("doctorId") int doctorId);

    @Modifying
    @Transactional
    @Query("delete from Appointment a where a.patient.patientId = :patientId")
    void deleteByPatientId(@Param("patientId") int patientId);

    @Modifying
    @Transactional
    @Query("delete from Appointment a where a.clinic.clinicId = :clinicId")
    void deleteByClinicId(@Param("clinicId") int clinicId);
}