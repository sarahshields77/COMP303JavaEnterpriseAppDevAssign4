package com.example.brendan_sarah_comp303_assignment4.repositories;

import com.example.brendan_sarah_comp303_assignment4.entities.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
}
