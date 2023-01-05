package com.drones.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class History {
	@Id
	@SequenceGenerator(name="history_sequence",sequenceName = "history_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "history_sequence")
	private Long id;
	private Long droneId;
	private Integer batteryLevel;
	private String state;
	private LocalDateTime checkTime;
	public History(Long droneId, Integer batteryLevel, String state, LocalDateTime checkTime) {
		super();
		this.droneId = droneId;
		this.batteryLevel = batteryLevel;
		this.state = state;
		this.checkTime = checkTime;
	}
	public Long getDroneId() {
		return droneId;
	}
	public void setDroneId(Long droneId) {
		this.droneId = droneId;
	}
	public Integer getBatteryLevel() {
		return batteryLevel;
	}
	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public LocalDateTime getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(LocalDateTime checkTime) {
		this.checkTime = checkTime;
	}
	
	
	
}
