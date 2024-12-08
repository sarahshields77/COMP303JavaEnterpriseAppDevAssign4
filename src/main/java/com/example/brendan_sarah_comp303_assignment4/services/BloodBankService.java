/*
 * Assign 4 Group:
 * Student 1: Brendan James 301257167
 * Student 2: Sarah Shields 301264350
 * Submission Date: December 9th, 2024
 */

package com.example.brendan_sarah_comp303_assignment4.services;

import com.example.brendan_sarah_comp303_assignment4.entities.BloodBank;
import com.example.brendan_sarah_comp303_assignment4.exceptions.ResourceNotFoundException;
import com.example.brendan_sarah_comp303_assignment4.repositories.BloodBankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodBankService {
   private final BloodBankRepository bloodBankRepository;

   public BloodBankService(BloodBankRepository bloodBankRepository) {
       this.bloodBankRepository = bloodBankRepository;
   }

   //POST
    public BloodBank saveBloodBank(BloodBank bloodBank) {
        return bloodBankRepository.save(bloodBank);
    }

    //GET
    public List<BloodBank> getAllBloodBanks() {
        return bloodBankRepository.findAll();
    }

    //PUT
    public BloodBank updateBloodBank(Long id, BloodBank bloodBankDetails) {
        BloodBank bloodBank = bloodBankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodBank not found"));
        bloodBank.setBloodbankName(bloodBankDetails.getBloodbankName());
        bloodBank.setAddress(bloodBankDetails.getAddress());
        bloodBank.setCity(bloodBankDetails.getCity());
        bloodBank.setPhone(bloodBankDetails.getPhone());
        bloodBank.setWebsite(bloodBankDetails.getWebsite());
        bloodBank.setEmail(bloodBankDetails.getEmail());
        return bloodBankRepository.save(bloodBank);
    }

    //DELETE
    public void deleteBloodBank(Long id) {
        BloodBank bloodBank = bloodBankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodBank not found"));
        bloodBankRepository.delete(bloodBank);
    }

    public BloodBank findById(Long id) {
        return bloodBankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodBank not found"));
    }
}
