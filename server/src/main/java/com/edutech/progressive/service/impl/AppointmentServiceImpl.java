package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Appointment;
import com.edutech.progressive.repository.AppointmentRepository;
import com.edutech.progressive.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Integer createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment).getAppointmentId();
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        if (appointment == null) return;
        if (appointment.getAppointmentId() == 0) return;
        if (!appointmentRepository.existsById(appointment.getAppointmentId())) return;
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentRepository.findByAppointmentId(appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentByClinic(int clinicId) {
        return appointmentRepository.findByClinic_ClinicId(clinicId);
    }

    @Override
    public List<Appointment> getAppointmentByPatient(int patientId) {
        return appointmentRepository.findByPatient_PatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }
}