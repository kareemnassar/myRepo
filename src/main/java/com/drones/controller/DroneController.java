package com.drones.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drones.dto.DroneDTO;
import com.drones.dto.LoadDroneDTO;
import com.drones.service.DroneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/drone")

public class DroneController {

	private final DroneService droneService;
	Logger log=LoggerFactory.getLogger(DroneController.class);
	@Autowired
	public DroneController(DroneService droneService) {
		super();
		this.droneService = droneService;
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> getAllDrones() {
		log.info("find all drones");
		return ResponseEntity.ok(droneService.getAllDrones());
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		
		return ResponseEntity.ok(droneService.getById(id));
	}
	@GetMapping("/findAvailableDrones")
	public ResponseEntity<?> getAvailableDrones() {
		log.info("find all available drones");
		return ResponseEntity.ok(droneService.getAvailableDrones());
	}
	
	@GetMapping("/battery/{id}")
	public ResponseEntity<?> getDroneBatteryLevel(@PathVariable Long id) {
		log.info("get drone battery");
		return ResponseEntity.ok(droneService.getDroneBatteryLevel(id));
	}
	
	@GetMapping("/loadedMedications/{id}")
	public ResponseEntity<?>getloadedDrone(@PathVariable Long id) {
		log.info("load drone with medications");
		return ResponseEntity.ok(droneService.checkLodedDrone(id));
	}

	@PostMapping("/add")
	public ResponseEntity<?> addDrone(@RequestBody @Valid DroneDTO dto) {
		
		DroneDTO insertedDrone=droneService.addDrone(dto);
		
		return ResponseEntity.ok(insertedDrone);
	}

	@PutMapping("/edit")
	public ResponseEntity<?> updateDrone(@RequestBody @Valid  DroneDTO dto) {
		
		DroneDTO insertedDrone=droneService.updateDrone(dto);
		
		return ResponseEntity.ok(insertedDrone);
	}
	
	@PostMapping("/load")
	public ResponseEntity<?> loadDrone(@RequestBody  LoadDroneDTO dto)  {
		
		LoadDroneDTO insertedDrone=droneService.LoadDrone(dto);
		
		return ResponseEntity.ok(insertedDrone);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>  deleteDrone(@PathVariable Long id) {
		log.info("delete drone with id {}",id);
		return ResponseEntity.ok(droneService.deleteDrone(id));
	}

}
