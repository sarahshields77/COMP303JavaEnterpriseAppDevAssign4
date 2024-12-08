/*
 * Assign 4 Group:
 * Student 1: Brendan James 301257167
 * Student 2: Sarah Shields 301264350
 * Submission Date: December 9th, 2024
 */

package com.example.brendan_sarah_comp303_assignment4.services;

import com.example.brendan_sarah_comp303_assignment4.entities.BloodBank;
import com.example.brendan_sarah_comp303_assignment4.entities.BloodStock;
import com.example.brendan_sarah_comp303_assignment4.entities.Donor;
import com.example.brendan_sarah_comp303_assignment4.exceptions.ResourceNotFoundException;
import com.example.brendan_sarah_comp303_assignment4.repositories.BloodStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodStockService {
    private final BloodStockRepository bloodStockRepository;

    public BloodStockService(BloodStockRepository bloodStockRepository) {
        this.bloodStockRepository = bloodStockRepository;
    }

    //POST
    public BloodStock saveBloodStock(BloodStock bloodStock) {
        return bloodStockRepository.save(bloodStock);
    }

    //GET
    public List<BloodStock> getAllBloodStock() {
        return bloodStockRepository.findAll();
    }

    //PUT
    public BloodStock updateBloodStock(Long id, BloodStock bloodStockDetails) {
        BloodStock bloodStock = bloodStockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodStock not found"));
        bloodStock.setBloodGroup(bloodStockDetails.getBloodGroup());
        bloodStock.setQuantity(bloodStockDetails.getQuantity());
        bloodStock.setBestBefore(bloodStockDetails.getBestBefore());
        bloodStock.setStatus(bloodStockDetails.getStatus());
        return bloodStockRepository.save(bloodStock);
    }

    //DELETE
    public void deleteBloodStock(Long id) {
        BloodStock bloodStock = bloodStockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodStock not found"));
        bloodStockRepository.delete(bloodStock);
    }


    public boolean checkBloodAvailability(String bloodGroup) {
        return bloodStockRepository.findAll()
                .stream()
                .anyMatch(stock -> stock.getBloodGroup().equalsIgnoreCase(bloodGroup) && stock.getQuantity() > 0);
    }

    public List<BloodStock> getBloodStocksByDonor(Donor donor) {
        return bloodStockRepository.findByDonor(donor);
    }

    public BloodStock findById(Long id) {
        return bloodStockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodBank not found"));
    }
}
