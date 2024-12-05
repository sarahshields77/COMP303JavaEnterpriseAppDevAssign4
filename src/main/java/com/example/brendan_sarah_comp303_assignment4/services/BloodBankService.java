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

    public BloodBank saveBloodBank(BloodBank bloodBank) {
        return bloodBankRepository.save(bloodBank);
    }

    public List<BloodBank> getAllBloodBanks() {
        return bloodBankRepository.findAll();
    }

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

    public void deleteBloodBank(Long id) {
        BloodBank bloodBank = bloodBankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodBank not found"));
        bloodBankRepository.delete(bloodBank);
    }
}
