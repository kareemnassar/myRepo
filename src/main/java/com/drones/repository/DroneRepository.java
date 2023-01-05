package com.drones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.drones.entity.Drone;
import com.drones.entity.Medication;

public interface DroneRepository extends JpaRepository<Drone, Long>{

	@Query(value="select drone from Drone drone where drone.state in('IDLE','LOADING') and drone.batteryCapacity >= 25")
	public List<Drone> findAvailableDrones();
	
	@Query(value="select drone.batteryCapacity from Drone drone where drone.id =:id")
	public String checkDroneBattery(@Param(value = "id") Long id);
	
	@Query(value="select drone.medications from Drone drone where "
			+ " drone.id =:id")
	public List<Medication> findLoadedDrone(@Param(value = "id") Long id);
	
}
