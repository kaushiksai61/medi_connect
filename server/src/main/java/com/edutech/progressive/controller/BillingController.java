package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Billing;
import com.edutech.progressive.service.BillingService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping
    public ResponseEntity<List<Billing>> getAllBills() {
        try {
            return ResponseEntity.ok(billingService.getAllBills());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createBill(@RequestBody Billing billing) {
        try {
            Integer id = billingService.createBill(billing);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{billingID}")
    public ResponseEntity<Void> deleteBill(@PathVariable("billingID") int billingId) {
        try {
            Billing b = billingService.getBillById(billingId);
            if (b == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            billingService.deleteBill(billingId);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{billingID}")
    public ResponseEntity<Billing> getBillById(@PathVariable("billingID") int billingId) {
        try {
            Billing bill = billingService.getBillById(billingId);
            if (bill == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(bill);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/patient/{patientID}")
    public ResponseEntity<List<Billing>> getBillsByPatient(@PathVariable("patientID") int patientId) {
        try {
            List<Billing> list = billingService.getBillsByPatient(patientId);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
