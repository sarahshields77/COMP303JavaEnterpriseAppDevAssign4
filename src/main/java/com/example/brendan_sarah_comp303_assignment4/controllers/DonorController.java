/*
 * Assign 4 Group:
 * Student 1: Brendan James 301257167
 * Student 2: Sarah Shields 301264350
 * Submission Date: December 9th, 2024
 */

package com.example.brendan_sarah_comp303_assignment4.controllers;

import com.example.brendan_sarah_comp303_assignment4.dto.LoginRequest;
import com.example.brendan_sarah_comp303_assignment4.entities.Donor;
import com.example.brendan_sarah_comp303_assignment4.services.DonorService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "http://localhost:3000")
public class DonorController {
    private final DonorService donorService;

    public DonorController(DonorService donorService) { this.donorService = donorService; }

    //POST
    @PostMapping
    public ResponseEntity<?> addDonor(@RequestBody @Valid Donor donor) {
        // Check if the username is already taken
        if (donorService.isUsernameTaken(donor.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Error("Username is already taken"));
        }

        // Check if the password is at least 6 characters long
        if (donor.getPassword().length() < 6) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Error("Password must be at least 6 characters long"));
        }

        // Save the donor if all validations pass
        Donor savedDonor = donorService.saveDonor(donor);
        return ResponseEntity.ok(savedDonor);
    }

    //GET
    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        return ResponseEntity.ok(donorService.getAllDonors());
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody @Valid Donor donorDetails) {
        return ResponseEntity.ok(donorService.updateDonor(id, donorDetails));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodStock(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return ResponseEntity.noContent().build();
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<?> loginDonor(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Donor donor = donorService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

        if (donor != null && donor.getPassword().equals(loginRequest.getPassword())) {
            session.setAttribute("activeUser", donor);
            return ResponseEntity.ok(donor);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Error("Invalid username or password"));
    }
}
