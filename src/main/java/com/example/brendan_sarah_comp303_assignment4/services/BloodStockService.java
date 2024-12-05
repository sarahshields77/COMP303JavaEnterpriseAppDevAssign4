package com.example.brendan_sarah_comp303_assignment4.services;

import com.example.brendan_sarah_comp303_assignment4.entities.BloodStock;
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

    public BloodStock saveBloodStock(BloodStock bloodStock) {
        return bloodStockRepository.save(bloodStock);
    }

    public List<BloodStock> getAllBloodStock() {
        return bloodStockRepository.findAll();
    }

    public BloodStock updateBloodStock(Long id, BloodStock bloodStockDetails) {
        BloodStock bloodStock = bloodStockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodStock not found"));
        bloodStock.setBloodGroup(bloodStockDetails.getBloodGroup());
        bloodStock.setQuantity(bloodStockDetails.getQuantity());
        bloodStock.setBestBefore(bloodStockDetails.getBestBefore());
        bloodStock.setStatus(bloodStockDetails.getStatus());
        return bloodStockRepository.save(bloodStock);
    }

    public void deleteBloodStock(Long id) {
        BloodStock bloodStock = bloodStockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodStock not found"));
        bloodStockRepository.delete(bloodStock);
    }

    public boolean checkBloodAvailability(String bloodGroup) {
        return bloodStockRepository.findAll()
                .stream()
                .anyMatch(stock -> stock.getBloodGroup().equalsIgnoreCase(bloodGroup) && stock.getQuantity() > 0);
    }
}
