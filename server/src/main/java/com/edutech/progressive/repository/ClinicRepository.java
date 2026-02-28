package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    Clinic findByClinicId(Integer clinicId);

    List<Clinic> findAllByLocation(String location);

    @Query("SELECT c FROM Clinic c WHERE c.doctor.doctorId = :doctorId")
    List<Clinic> findAllByDoctorId(@Param("doctorId") Integer doctorId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Clinic c WHERE c.doctor.doctorId = :doctorId")
    void deleteByDoctorId(@Param("doctorId") Integer doctorId);
}