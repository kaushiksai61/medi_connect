package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Clinic;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    Clinic findByClinicId(int clinicId);

    List<Clinic> findAllByLocation(String location);

    @Query("select c from Clinic c where c.doctor.doctorId = :doctorId")
    List<Clinic> findAllByDoctorId(@Param("doctorId") int doctorId);

    @Modifying
    @Transactional
    @Query("delete from Clinic c where c.doctor.doctorId = :doctorId")
    void deleteByDoctorId(@Param("doctorId") int doctorId);

    Clinic findByClinicName(String clinicName);
}