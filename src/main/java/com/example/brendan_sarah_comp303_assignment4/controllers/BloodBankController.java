package com.example.brendan_sarah_comp303_assignment4.controllers;

import com.example.brendan_sarah_comp303_assignment4.entities.BloodBank;
import com.example.brendan_sarah_comp303_assignment4.services.BloodBankService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloodbanks")
@CrossOrigin(origins = "http://localhost:3000")
public class BloodBankController {
    private final BloodBankService bloodBankService;

    public BloodBankController(BloodBankService bloodBankService) {
        this.bloodBankService = bloodBankService;
    }

    //POST
    @PostMapping
    public ResponseEntity<BloodBank> addBloodBank(@RequestBody @Valid BloodBank bloodBank) {
        return ResponseEntity.ok(bloodBankService.saveBloodBank(bloodBank));
    }

    //GET
    @GetMapping
    public ResponseEntity<List<BloodBank>> getAllBloodBanks() {
        return ResponseEntity.ok(bloodBankService.getAllBloodBanks());
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<BloodBank> updateBloodBank(@PathVariable Long id, @RequestBody @Valid BloodBank bloodBankDetails) {
        return ResponseEntity.ok(bloodBankService.updateBloodBank(id, bloodBankDetails));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodBank(@PathVariable Long id) {
        bloodBankService.deleteBloodBank(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodBank> getBloodBankById(@PathVariable Long id) {
        BloodBank bloodBank = bloodBankService.findById(id);
        if (bloodBank == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(bloodBank);
    }
}