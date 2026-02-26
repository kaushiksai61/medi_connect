package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}