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

    //POST
    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    //GET
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    //PUT
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

    //DELETE
    public void deleteDonor(Long id) {
        Donor donor = donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Donor not found"));
        donorRepository.delete(donor);
    }

    //Login
    public Donor findByUsernameAndPassword(String username, String password) {
        return donorRepository.findByUsernameAndPassword(username, password);
    }

    public boolean isUsernameTaken(String username) {
        return donorRepository.existsByUsername(username);
    }

    public Donor findDonorById(Long id) {
        return donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodBank not found"));
    }
}
