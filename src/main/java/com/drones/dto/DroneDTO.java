package com.drones.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import com.drones.base.BaseEntity;
import com.drones.validator.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Validated
public class DroneDTO extends BaseEntity {
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
	
	public DroneDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DroneDTO(
			@NotBlank(message = "serial number can not be null") @Length(min = 1, max = 100) String serialNumber,
			@NotBlank(message = "model can not be null") String model,
			@NotNull(message = "weightLimit can not be null") @Min(value = 1, message = "weight limit must be greater than 1") @Max(value = 500, message = "weight limit must be less than or equals to 500") Integer weightLimit,
			@NotNull(message = "battery capacity can not be null") @Min(value = 0, message = "battery capacity must be greater than 0") @Max(value = 100, message = "battery capacity must be less than or equals to 100") Integer batteryCapacity) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
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
	
	
}
