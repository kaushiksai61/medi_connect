package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.exception.PatientAlreadyExistsException;
import com.edutech.progressive.exception.PatientNotFoundException;
import com.edutech.progressive.service.PatientService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Void> handlePatientNotFound(PatientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(PatientAlreadyExistsException.class)
    public ResponseEntity<Void> handlePatientExists(PatientAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        try {
            return ResponseEntity.ok(patientService.getAllPatients());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{patientID}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("patientID") int patientId) {
        try {
            return ResponseEntity.ok(patientService.getPatientById(patientId));
        } catch (PatientNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addPatient(@RequestBody Patient patient) {
        try {
            Integer id = patientService.addPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (PatientAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{patientID}")
    public ResponseEntity<Void> updatePatient(@PathVariable("patientID") int patientId, @RequestBody Patient patient) {
        try {
            patient.setPatientId(patientId);
            patientService.updatePatient(patient);
            return ResponseEntity.ok().build();
        } catch (PatientNotFoundException | PatientAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{patientID}")
    public ResponseEntity<Void> deletePatient(@PathVariable("patientID") int patientId) {
        try {
            patientService.deletePatient(patientId);
            return ResponseEntity.noContent().build();
        } catch (PatientNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}