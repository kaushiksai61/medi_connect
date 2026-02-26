package com.edutech.progressive.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Appointment;
import com.edutech.progressive.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(service.add(appointment));
    }

    @GetMapping("/{id}")
    public Optional<Appointment> get(@PathVariable Long id) {
        return service.findById(id);
    }
}