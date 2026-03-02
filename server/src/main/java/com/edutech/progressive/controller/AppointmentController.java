package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Appointment;
import com.edutech.progressive.service.AppointmentService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointment() {
        try {
            return ResponseEntity.ok(appointmentService.getAllAppointments());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createAppointment(@RequestBody Appointment appointment) {
        try {
            Integer id = appointmentService.createAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{appointmentID}")
    public ResponseEntity<Void> updateAppointment(@PathVariable("appointmentID") int appointmentId, @RequestBody Appointment appointment) {
        try {
            appointment.setAppointmentId(appointmentId);
            appointmentService.updateAppointment(appointment);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{appointmentID}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("appointmentID") int appointmentId) {
        try {
            Appointment a = appointmentService.getAppointmentById(appointmentId);
            return ResponseEntity.ok(a);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/clinic/{clinicID}")
    public ResponseEntity<List<Appointment>> getAppointmentByClinic(@PathVariable("clinicID") int clinicId) {
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentByClinic(clinicId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/patient/{patientID}")
    public ResponseEntity<List<Appointment>> getAppointmentByPatient(@PathVariable("patientID") int patientId) {
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentByPatient(patientId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Appointment>> getAppointmentByStatus(@PathVariable("status") String status) {
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentByStatus(status));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}