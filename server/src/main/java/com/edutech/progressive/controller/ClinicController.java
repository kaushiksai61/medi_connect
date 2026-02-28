package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.service.ClinicService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinic")
public class ClinicController {

    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    public ResponseEntity<List<Clinic>> getAllClinics() {
        try {
            return ResponseEntity.ok(clinicService.getAllClinics());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{clinicID}")
    public ResponseEntity<Clinic> getClinicById(@PathVariable("clinicID") int clinicId) {
        try {
            Clinic clinic = clinicService.getClinicById(clinicId);
            return ResponseEntity.ok(clinic);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addClinic(@RequestBody Clinic clinic) {
        try {
            Integer id = clinicService.addClinic(clinic);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{clinicID}")
    public ResponseEntity<Void> updateClinic(@PathVariable("clinicID") int clinicId, @RequestBody Clinic clinic) {
        try {
            clinic.setClinicId(clinicId);
            clinicService.updateClinic(clinic);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{clinicID}")
    public ResponseEntity<Void> deleteClinic(@PathVariable("clinicID") int clinicId) {
        try {
            clinicService.deleteClinic(clinicId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Clinic>> getAllClinicByLocation(@PathVariable("location") String location) {
        try {
            return ResponseEntity.ok(clinicService.getAllClinicByLocation(location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Clinic>> getAllClinicByDoctorId(@PathVariable("doctorId") int doctorId) {
        try {
            return ResponseEntity.ok(clinicService.getAllClinicByDoctorId(doctorId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}