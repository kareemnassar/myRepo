package com.drones.dto;

import java.util.List;

import com.drones.base.BaseEntity;
import com.drones.entity.Medication;

import jakarta.persistence.Id;

public class LoadDroneDTO extends BaseEntity{
	@Id
	private Long id;
	private List<Medication> medications;
	
	public LoadDroneDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoadDroneDTO(Long id, List<Medication> medications) {
		super();
		this.id = id;
		this.medications = medications;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Medication> getMedications() {
		return medications;
	}
	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}
	
}
