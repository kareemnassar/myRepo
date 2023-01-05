package com.drones.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.drones.entity.Medication;
import com.drones.service.MedicationService;

@RestController
@RequestMapping("/api/v1/medication")
public class MedicationController {
	private final MedicationService medicationService;

	@Autowired
	public MedicationController(MedicationService medicationService) {
		super();
		this.medicationService = medicationService;
	}

	@GetMapping("/findAll")
	public List<Medication> getAll() {

		return medicationService.getAll();
	}

	@PostMapping("/add")
	public ResponseEntity<?> addMedication(@RequestBody Medication medication) {

		return ResponseEntity.ok(medicationService.addMedication(medication));
	}
	@PostMapping("/uploadimage")
	public ResponseEntity<?> uploadMedicationImage(@RequestParam Long id,@RequestParam MultipartFile image) 
			throws IOException {

		return ResponseEntity.ok(medicationService.uploadMedicationImage(id,image));
	}

	@PutMapping("/edit")
	public ResponseEntity<?> updateMedication(@RequestBody Medication medication) {

		return ResponseEntity.ok(medicationService.addMedication(medication));
	}
	@DeleteMapping("/delete/{id}")
	public String deleteMedication(@PathVariable Long id) {
		return medicationService.deleteMedication(id);
	}

}
