package com.edutech.progressive.entity;

import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_id")
    private int billingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Patient patient;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private Double amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_issue", nullable = false)
    private Date dateOfIssue;

    @Temporal(TemporalType.DATE)
    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    public Billing() {}

    public Billing(int billingId, Patient patient, Double amount, Date dateOfIssue, Date dueDate, String status) {
        this.billingId = billingId;
        this.patient = patient;
        this.amount = amount;
        this.dateOfIssue = dateOfIssue;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getBillingId() {
        return billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}