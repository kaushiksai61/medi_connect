package com.edutech.progressive.service.impl;

import com.edutech.progressive.dao.ClinicDAO;
import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.ClinicService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicServiceImplJdbc implements ClinicService {

    private final ClinicDAO clinicDAO;

    public ClinicServiceImplJdbc(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }

    @Override
    public List<Clinic> getAllClinics() throws Exception {
        try {
            return clinicDAO.getAllClinics();
        } catch (SQLException e) {
            throw new Exception("Failed to fetch clinics", e);
        }
    }

    @Override
    public Clinic getClinicById(int clinicId) throws Exception {
        try {
            return clinicDAO.getClinicById(clinicId);
        } catch (SQLException e) {
            throw new Exception("Failed to fetch clinic by id: " + clinicId, e);
        }
    }

    @Override
    public Integer addClinic(Clinic clinic) throws Exception {
        try {
            return clinicDAO.addClinic(clinic);
        } catch (SQLException e) {
            throw new Exception("Failed to add clinic", e);
        }
    }

    @Override
    public void updateClinic(Clinic clinic) throws Exception {
        try {
            clinicDAO.updateClinic(clinic);
        } catch (SQLException e) {
            throw new Exception("Failed to update clinic", e);
        }
    }

    @Override
    public void deleteClinic(int clinicId) throws Exception {
        try {
            clinicDAO.deleteClinic(clinicId);
        } catch (SQLException e) {
            throw new Exception("Failed to delete clinic", e);
        }
    }

    @Override
    public List<Clinic> getAllClinicByLocation(String location) throws Exception {
        try {
            List<Clinic> all = clinicDAO.getAllClinics();
            List<Clinic> result = new ArrayList<>();
            if (all != null) {
                for (Clinic c : all) {
                    if (c != null && location != null && location.equals(c.getLocation())) {
                        result.add(c);
                    }
                }
            }
            return result;
        } catch (SQLException e) {
            throw new Exception("Failed to fetch clinics by location", e);
        }
    }

    @Override
    public List<Clinic> getAllClinicByDoctorId(int doctorId) throws Exception {
        try {
            List<Clinic> all = clinicDAO.getAllClinics();
            List<Clinic> result = new ArrayList<>();
            if (all != null) {
                for (Clinic c : all) {
                    Doctor d = c != null ? c.getDoctor() : null;
                    if (d != null && d.getDoctorId() == doctorId) {
                        result.add(c);
                    }
                }
            }
            return result;
        } catch (SQLException e) {
            throw new Exception("Failed to fetch clinics by doctorId", e);
        }
    }
}