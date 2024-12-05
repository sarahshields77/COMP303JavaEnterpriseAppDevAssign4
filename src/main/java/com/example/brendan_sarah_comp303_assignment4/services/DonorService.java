package com.example.brendan_sarah_comp303_assignment4.services;

import com.example.brendan_sarah_comp303_assignment4.entities.Donor;
import com.example.brendan_sarah_comp303_assignment4.exceptions.ResourceNotFoundException;
import com.example.brendan_sarah_comp303_assignment4.repositories.DonorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorService {
    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor updateDonor(Long id, Donor donorDetails) {
        Donor donor = donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Donor not found"));
        donor.setFirstName(donorDetails.getFirstName());
        donor.setLastName(donorDetails.getLastName());
        donor.setAge(donorDetails.getAge());
        donor.setGender(donorDetails.getGender());
        donor.setBloodGroup(donorDetails.getBloodGroup());
        donor.setCity(donorDetails.getCity());
        donor.setPhone(donorDetails.getPhone());
        return donorRepository.save(donor);
    }

    public void deleteDonor(Long id) {
        Donor donor = donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Donor not found"));
        donorRepository.delete(donor);
    }
}
