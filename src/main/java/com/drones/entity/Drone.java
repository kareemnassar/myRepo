package com.drones.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.drones.base.BaseEntity;
import com.drones.validator.Model;
import com.drones.validator.State;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Drone extends BaseEntity{
	@Id
	@SequenceGenerator(name="drone_sequence",sequenceName = "drone_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "drone_sequence")
	private Long id;
	@NotBlank(message = "serial number can not be null")
	@Length(min = 1,max = 100)
	private String serialNumber;
	@NotBlank(message = "model can not be null")
	@Model(acceptedValues = {"Lightweight", "Middleweight", "Cruiserweight", "Heavyweight"} )
	private String model;
	@NotNull(message = "weightLimit can not be null")
	@Min(value = 1,message = "weight limit must be greater than 1")
	@Max(value = 500,message = "weight limit must be less than or equals to 500")
	private Integer weightLimit;
	@NotNull(message = "battery capacity can not be null")
	@Min(value = 0,message = "battery capacity must be greater than 0")
	@Max(value = 100,message = "battery capacity must be less than or equals to 100")
	private Integer batteryCapacity;
	@NotNull(message = "state can not be null")
	@State(acceptedValues = { "IDLE", "LOADING", "LOADED", "DELIVERING","DELIVERED", "RETURNING" })
	private String state;
	@OneToMany(mappedBy = "drone", cascade = {CascadeType.ALL})
	@JsonManagedReference
	private List<Medication> medications;
	public Drone() {
		super();
		this.state = "IDLE";
		// TODO Auto-generated constructor stub
	}
	public Drone(String serialNumber, String model, Integer weightLimit, Integer batteryCapacity) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = "IDLE";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getWeightLimit() {
		return weightLimit;
	}
	public void setWeightLimit(Integer weightLimit) {
		this.weightLimit = weightLimit;
	}
	public Integer getBatteryCapacity() {
		return batteryCapacity;
	}
	public void setBatteryCapacity(Integer batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	public List<Medication> getMedications() {
		return medications;
	}
	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}
	@Override
	public String toString() {
		return "Drone [id=" + id + ", serialNumber=" + serialNumber + ", model=" + model + ", weightLimit="
				+ weightLimit + ", batteryCapacity=" + batteryCapacity + ", state=" + state + "]";
	}
	

}
