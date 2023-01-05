package com.drones.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.drones.entity.Medication;
import com.drones.repository.MedicationRepository;
import com.drones.utility.ImageUtility;

@Service
public class MedicationService {
	private final MedicationRepository medicationRepository;
	Logger log=LoggerFactory.getLogger(MedicationService.class);
	@Autowired
	public MedicationService(MedicationRepository medicationRepository) {
		super();
		this.medicationRepository = medicationRepository;
	}

	public List<Medication> getAll() {
		List<Medication> medications =medicationRepository.findAll();
			if(medications.isEmpty()) {
				throw new IllegalStateException("No medications found");
			}
		return medications;
	}

	public Medication addMedication(Medication medication) {

		return medicationRepository.save(medication);
	}

	public String deleteMedication(Long id) {

		medicationRepository.deleteById(id);

		return "Medecation with id :" + id + " deleted successfully";
	}

	public void addListOfMedications(List<Medication> medications) {
		// TODO Auto-generated method stub
		medicationRepository.saveAll(medications);
	}

	public Medication uploadMedicationImage(Long id, MultipartFile image) throws IOException {
		log.info("start uploading image");
		Optional<Medication> med =medicationRepository.findById(id);
		if(med.isEmpty()) {
			log.error("No medication found with id {}",id);
			throw new IllegalStateException("No medication found with id : "+id);
		}
		Medication medication=med.get();
		medication.setImage(ImageUtility.compressImage(image.getBytes()));
		medicationRepository.save(medication);
		return medication;
	}


}
