package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.exception.DoctorAlreadyExistsException;
import com.edutech.progressive.service.DoctorService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @ExceptionHandler(DoctorAlreadyExistsException.class)
    public ResponseEntity<Void> handleDoctorExists(DoctorAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        try {
            return ResponseEntity.ok(doctorService.getAllDoctors());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{doctorID}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorID") int doctorId) {
        try {
            Doctor doctor = doctorService.getDoctorById(doctorId);
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addDoctor(@RequestBody Doctor doctor) {
        try {
            Integer id = doctorService.addDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (DoctorAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{doctorID}")
    public ResponseEntity<Void> updateDoctor(@PathVariable("doctorID") int doctorId, @RequestBody Doctor doctor) {
        try {
            doctor.setDoctorId(doctorId);
            doctorService.updateDoctor(doctor);
            return ResponseEntity.ok().build();
        } catch (DoctorAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{doctorID}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("doctorID") int doctorId) {
        try {
            doctorService.deleteDoctor(doctorId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/experience")
    public ResponseEntity<List<Doctor>> getDoctorSortedByExperience() {
        try {
            return ResponseEntity.ok(doctorService.getDoctorSortedByExperience());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}