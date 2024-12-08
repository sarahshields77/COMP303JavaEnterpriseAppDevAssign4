/*
 * Assign 4 Group:
 * Student 1: Brendan James 301257167
 * Student 2: Sarah Shields 301264350
 * Submission Date: December 9th, 2024
 */

package com.example.brendan_sarah_comp303_assignment4.controllers;

import com.example.brendan_sarah_comp303_assignment4.dto.BloodStockRequest;
import com.example.brendan_sarah_comp303_assignment4.entities.BloodBank;
import com.example.brendan_sarah_comp303_assignment4.entities.BloodStock;
import com.example.brendan_sarah_comp303_assignment4.entities.Donor;
import com.example.brendan_sarah_comp303_assignment4.services.BloodBankService;
import com.example.brendan_sarah_comp303_assignment4.services.BloodStockService;
import com.example.brendan_sarah_comp303_assignment4.services.DonorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bloodstocks")
@CrossOrigin(origins = "http://localhost:3000")
public class BloodStockController {

    @Autowired
    private BloodStockService bloodStockService;

    @Autowired
    private DonorService donorService;

    @Autowired
    private BloodBankService bloodBankService;

    //POST
    @PostMapping
    public ResponseEntity<?> createBloodStock(@RequestBody BloodStockRequest request) {
        try {

            Donor donor = donorService.findDonorById(request.getDonorId());
            BloodBank bloodBank = bloodBankService.findById(request.getBloodBankId());

            BloodStock bloodStock = new BloodStock();
            bloodStock.setBloodGroup(donor.getBloodGroup());
            bloodStock.setQuantity(request.getQuantity());
            bloodStock.setAppointmentDate(request.getAppointmentDate());
            bloodStock.setDonor(donor);
            bloodStock.setBloodBank(bloodBank);

            if (request.getAppointmentDate().isBefore(LocalDate.now())) {
                bloodStock.setStatus("Taken");
                bloodStock.setBestBefore(request.getAppointmentDate().plusYears(1));
            } else {
                bloodStock.setStatus("Scheduled");
                bloodStock.setBestBefore(request.getAppointmentDate().plusYears(1));
            }

            bloodStockService.saveBloodStock(bloodStock);

            return ResponseEntity.ok("Blood stock created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    //GET
    @GetMapping
    public ResponseEntity<List<BloodStock>> getAllBloodStock() {
        return ResponseEntity.ok(bloodStockService.getAllBloodStock());
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodStock(@PathVariable Long id) {
        bloodStockService.deleteBloodStock(id);
        return ResponseEntity.noContent().build();
    }

    //GET by BloodGroup
    @GetMapping("/availability/{bloodGroup}")
    public ResponseEntity<Boolean> checkBloodAvailability(@PathVariable String bloodGroup) {
        System.out.println(bloodGroup);
        return ResponseEntity.ok(bloodStockService.checkBloodAvailability(bloodGroup));
    }

    @PostMapping("/donations/{id}")
    public List<BloodStock> getBloodStocksByDonor(@RequestBody Donor donor) {
        System.out.println(donor);
        return bloodStockService.getBloodStocksByDonor(donor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodStock> getDonationById(@PathVariable Long id) {
        try {
            BloodStock bloodStock = bloodStockService.findById(id); // Assuming you have a method to find by ID
            if (bloodStock == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(bloodStock);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodStock> updateBloodStock(@PathVariable Long id, @RequestBody @Valid BloodStock bloodStockDetails) {
        try {
            BloodStock existingBloodStock = bloodStockService.findById(id);
            if (existingBloodStock == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Donor donor = donorService.findDonorById(bloodStockDetails.getDonor().getId());
            BloodBank bloodBank = bloodBankService.findById(bloodStockDetails.getBloodBank().getId());

            existingBloodStock.setBloodGroup(donor.getBloodGroup());
            existingBloodStock.setQuantity(bloodStockDetails.getQuantity());
            existingBloodStock.setAppointmentDate(bloodStockDetails.getAppointmentDate());
            existingBloodStock.setDonor(donor);
            existingBloodStock.setBloodBank(bloodBank);

            if (bloodStockDetails.getAppointmentDate().isBefore(LocalDate.now())) {
                existingBloodStock.setStatus("Taken");
                existingBloodStock.setBestBefore(bloodStockDetails.getAppointmentDate().plusYears(1));
            } else {
                // For future appointment, allow changing between Scheduled and Cancelled
                if ("Scheduled".equals(bloodStockDetails.getStatus())) {
                    existingBloodStock.setStatus("Scheduled");
                } else if ("Cancelled".equals(bloodStockDetails.getStatus())) {
                    existingBloodStock.setStatus("Cancelled");
                } else {
                    // If no valid status is passed, keep the previous status
                    existingBloodStock.setStatus(existingBloodStock.getStatus());
                }
                existingBloodStock.setBestBefore(bloodStockDetails.getAppointmentDate().plusYears(1));
            }

            bloodStockService.saveBloodStock(existingBloodStock);

            return ResponseEntity.ok(existingBloodStock);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}