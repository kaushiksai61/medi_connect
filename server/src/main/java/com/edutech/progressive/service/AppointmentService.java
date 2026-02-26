package com.edutech.progressive.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Appointment;
import com.edutech.progressive.repository.AppointmentRepository;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Appointment add(Appointment appointment) {
        return repository.save(appointment);
    }

    public Optional<Appointment> findById(Long id) {
        return repository.findById(id);
    }
}