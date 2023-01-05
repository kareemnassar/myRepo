package com.drones.entity;



import com.drones.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Medication extends BaseEntity{
	@Id
	@SequenceGenerator(name="medication_sequence",sequenceName = "medication_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "medication_sequence")
	private Long id;
	@NotBlank(message = "name can not be null")
	@Pattern(regexp = "^([A-Za-z0-9_-]+)$",message = "Name allowed only letters, numbers, ‘-‘, ‘_’")
	private String name;
	@NotBlank(message = "code can not be null")
	@Pattern(regexp = "^([A-Z0-9_-]+)$",message = "Code allowed only upper case letters, underscore and numbers")
	private String code;
	@NotNull(message = "weight can not be null")
	private Integer weight;
	@ManyToOne()
	@JsonBackReference
	//@JoinColumn(name = "drone_id",referencedColumnName = "id")
    private Drone drone;
	@Column(length = 1000000)
	private byte[] image;
	public Medication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Medication(String name, String code, Integer weight) {
		super();
		this.name = name;
		this.code = code;
		this.weight = weight;
	}
	
	
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Drone getDrone() {
		return drone;
	}
	public void setDrone(Drone drone) {
		this.drone = drone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
}
