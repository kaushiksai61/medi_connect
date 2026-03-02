package com.edutech.progressive.service;

import com.edutech.progressive.entity.Appointment;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAllAppointments();

    Integer createAppointment(Appointment appointment);

    void updateAppointment(Appointment appointment);

    Appointment getAppointmentById(int appointmentId);

    List<Appointment> getAppointmentByClinic(int clinicId);

    List<Appointment> getAppointmentByPatient(int patientId);

    List<Appointment> getAppointmentByStatus(String status);
}