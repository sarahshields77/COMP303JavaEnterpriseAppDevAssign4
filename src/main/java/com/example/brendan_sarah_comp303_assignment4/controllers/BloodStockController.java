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

            // Logic for status and bestBefore
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

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<BloodStock> updateBloodStock(@PathVariable Long id, @RequestBody @Valid BloodStock bloodStockDetails) {
        return ResponseEntity.ok(bloodStockService.updateBloodStock(id, bloodStockDetails));
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
        return ResponseEntity.ok(bloodStockService.checkBloodAvailability(bloodGroup));
    }

    @PostMapping("/donations/{id}")
    public List<BloodStock> getBloodStocksByDonor(@RequestBody Donor donor) {
        System.out.println(donor);
        return bloodStockService.getBloodStocksByDonor(donor);
    }
}