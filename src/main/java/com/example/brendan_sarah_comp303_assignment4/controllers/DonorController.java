package com.example.brendan_sarah_comp303_assignment4.controllers;

import com.example.brendan_sarah_comp303_assignment4.entities.Donor;
import com.example.brendan_sarah_comp303_assignment4.services.DonorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {
    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping
    public ResponseEntity<Donor> addDonor(@RequestBody @Valid Donor donor) {
        return ResponseEntity.ok(donorService.saveDonor(donor));
    }

    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        return ResponseEntity.ok(donorService.getAllDonors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody @Valid Donor donorDetails) {
        return ResponseEntity.ok(donorService.updateDonor(id, donorDetails));
    }

}
