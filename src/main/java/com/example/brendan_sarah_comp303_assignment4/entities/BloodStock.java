/*
 * Assign 4 Group:
 * Student 1: Brendan James 301257167
 * Student 2: Sarah Shields 301264350
 * Submission Date: December 9th, 2024
 */

package com.example.brendan_sarah_comp303_assignment4.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "blood_stocks")
public class BloodStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String bloodGroup;
    @NotNull
    private int quantity;
    @NotNull
    @Future
    private LocalDate bestBefore;
    @NotNull
    private String status;
    @ManyToOne
    @JoinColumn(name = "donor_id", referencedColumnName = "id", nullable = false)
    private Donor donor;
    @ManyToOne
    @JoinColumn(name = "bloodbank_id", referencedColumnName = "id", nullable = false)
    private BloodBank bloodBank;
    @NotNull
    private LocalDate appointmentDate;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(@NotNull String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @NotNull
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull int quantity) {
        this.quantity = quantity;
    }

    public @NotNull @Future LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(@NotNull @Future LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    public @NotNull String getStatus() {
        return status;
    }

    public void setStatus(@NotNull String status) {
        this.status = status;
    }

    public Donor getDonor() { return donor; }

    public void setDonor(Donor donor) { this.donor = donor; }

    public BloodBank getBloodBank() { return bloodBank; }

    public void setBloodBank(BloodBank bloodBank) { this.bloodBank = bloodBank; }

    public LocalDate getAppointmentDate() { return appointmentDate; }

    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
}