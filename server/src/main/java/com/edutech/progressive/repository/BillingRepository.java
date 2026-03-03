package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Billing;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BillingRepository extends JpaRepository<Billing, Integer> {

    Billing findByBillingId(int billingId);

    List<Billing> findByPatient_PatientId(int patientId);

    @Modifying
    @Transactional
    @Query("delete from Billing b where b.patient.patientId = :patientId")
    void deleteByPatientId(@Param("patientId") int patientId);
}