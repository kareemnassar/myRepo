package com.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drones.entity.Medication;

public interface MedicationRepository extends JpaRepository<Medication,Long>{

}
