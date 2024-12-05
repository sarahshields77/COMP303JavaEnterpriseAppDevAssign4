package com.example.brendan_sarah_comp303_assignment4.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
}