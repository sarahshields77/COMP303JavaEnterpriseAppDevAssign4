/*
 * Assign 4 Group:
 * Student 1: Brendan James 301257167
 * Student 2: Sarah Shields 301264350
 * Submission Date: December 9th, 2024
 */

package com.example.brendan_sarah_comp303_assignment4.repositories;

import com.example.brendan_sarah_comp303_assignment4.entities.BloodStock;
import com.example.brendan_sarah_comp303_assignment4.entities.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodStockRepository extends JpaRepository<BloodStock, Long> {
    List<BloodStock> findByDonor(Donor donor);
}
