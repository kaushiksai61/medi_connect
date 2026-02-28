package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.repository.ClinicRepository;
import com.edutech.progressive.service.ClinicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClinicServiceImplJpa implements ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicServiceImplJpa(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public List<Clinic> getAllClinics() throws Exception {
        return clinicRepository.findAll();
    }

    @Override
    public Clinic getClinicById(int clinicId) throws Exception {
        return clinicRepository.findByClinicId(clinicId);
    }

    @Override
    public Integer addClinic(Clinic clinic) throws Exception {
        return clinicRepository.save(clinic).getClinicId();
    }

    @Override
    public void updateClinic(Clinic clinic) throws Exception {
        if (clinic == null) return;
        if (clinic.getClinicId() == 0) return;
        if (!clinicRepository.existsById(clinic.getClinicId())) return;
        clinicRepository.save(clinic);
    }

    @Override
    public void deleteClinic(int clinicId) throws Exception {
        Clinic c = clinicRepository.findByClinicId(clinicId);
        if (c != null) clinicRepository.delete(c);
    }

    @Override
    public List<Clinic> getAllClinicByLocation(String location) throws Exception {
        return clinicRepository.findAllByLocation(location);
    }

    @Override
    public List<Clinic> getAllClinicByDoctorId(int doctorId) throws Exception {
        return clinicRepository.findAllByDoctorId(doctorId);
    }
}