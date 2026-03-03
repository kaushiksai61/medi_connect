package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Billing;
import com.edutech.progressive.repository.BillingRepository;
import com.edutech.progressive.service.BillingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;

    public BillingServiceImpl(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @Override
    public List<Billing> getAllBills() {
        return billingRepository.findAll();
    }

    @Override
    public Integer createBill(Billing billing) {
        return billingRepository.save(billing).getBillingId();
    }

    @Override
    public void deleteBill(int billingId) {
        Billing b = billingRepository.findByBillingId(billingId);
        if (b != null) {
            billingRepository.delete(b);
        }
    }

    @Override
    public Billing getBillById(int billingId) {
        return billingRepository.findByBillingId(billingId);
    }

    @Override
    public List<Billing> getBillsByPatient(int patientId) {
        return billingRepository.findByPatient_PatientId(patientId);
    }
}