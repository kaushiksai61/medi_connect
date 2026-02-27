package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.DoctorService;

@Service
public class DoctorServiceImplJpa implements DoctorService {
    public DoctorServiceImplJpa() {
    }

    public List<Doctor> getAllDoctors() throws Exception {
        return new ArrayList<>();
    }

    public Doctor getDoctorById(int doctorId) throws Exception {
        return null;
    }

    public Integer addDoctor(Doctor doctor) throws Exception {
        return -1;
    }

    public List<Doctor> getDoctorSortedByExperience() throws Exception {
        return new ArrayList<>();
    }

    public void updateDoctor(Doctor doctor) throws Exception {
    }

    public void deleteDoctor(int doctorId) throws Exception {
    }
}