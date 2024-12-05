package com.example.brendan_sarah_comp303_assignment4.controllers;

import com.example.brendan_sarah_comp303_assignment4.entities.BloodStock;
import com.example.brendan_sarah_comp303_assignment4.services.BloodStockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloodstocks")
public class BloodStockController {
    private final BloodStockService bloodStockService;

    public BloodStockController(BloodStockService bloodStockService) {
        this.bloodStockService = bloodStockService;
    }

    @PostMapping
    public ResponseEntity<BloodStock> addBloodStock(@RequestBody @Valid BloodStock bloodStock) {
        return ResponseEntity.ok(bloodStockService.saveBloodStock(bloodStock));
    }

    @GetMapping
    public ResponseEntity<List<BloodStock>> getAllBloodStock() {
        return ResponseEntity.ok(bloodStockService.getAllBloodStock());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodStock> updateBloodStock(@PathVariable Long id, @RequestBody @Valid BloodStock bloodStockDetails) {
        return ResponseEntity.ok(bloodStockService.updateBloodStock(id, bloodStockDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodStock(@PathVariable Long id) {
        bloodStockService.deleteBloodStock(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/availability/{bloodGroup}")
    public ResponseEntity<Boolean> checkBloodAvailability(@PathVariable String bloodGroup) {
        return ResponseEntity.ok(bloodStockService.checkBloodAvailability(bloodGroup));
    }
}