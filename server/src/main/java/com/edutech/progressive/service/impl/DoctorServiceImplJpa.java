package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.repository.ClinicRepository;
import com.edutech.progressive.repository.DoctorRepository;
import com.edutech.progressive.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImplJpa implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;

    @Autowired
    public DoctorServiceImplJpa(DoctorRepository doctorRepository, ClinicRepository clinicRepository) {
        this.doctorRepository = doctorRepository;
        this.clinicRepository = clinicRepository;
    }

    public DoctorServiceImplJpa(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
        this.clinicRepository = null;
    }

    @Override
    public List<Doctor> getAllDoctors() throws Exception {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(int doctorId) throws Exception {
        return doctorRepository.findByDoctorId(doctorId);
    }

    @Override
    public Integer addDoctor(Doctor doctor) throws Exception {
        return doctorRepository.save(doctor).getDoctorId();
    }

    @Override
    public void updateDoctor(Doctor doctor) throws Exception {
        if (doctor == null) return;
        if (!doctorRepository.existsById(doctor.getDoctorId())) return;
        doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(int doctorId) throws Exception {
        if (clinicRepository != null) {
            clinicRepository.deleteByDoctorId(doctorId);
        }
        Doctor d = doctorRepository.findByDoctorId(doctorId);
        if (d != null) {
            doctorRepository.delete(d);
        }
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() throws Exception {
        return doctorRepository.findAll(Sort.by(Sort.Direction.ASC, "yearsOfExperience"));
    }
}