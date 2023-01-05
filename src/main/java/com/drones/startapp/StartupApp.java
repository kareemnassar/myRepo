package com.drones.startapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.drones.entity.Drone;
import com.drones.entity.Medication;
import com.drones.repository.DroneRepository;

@Component
public class StartupApp implements CommandLineRunner{
	@Autowired
	private DroneRepository droneRepository;
	@Override
	public void run(String... args) throws Exception {

		Drone d1=new Drone();
		Medication m1= new Medication();
		d1.setBatteryCapacity(100);
		d1.setModel("CruiserweighT");
		d1.setSerialNumber("sda");
		d1.setWeightLimit(500);
		d1.setState("LOADING");
		m1.setCode("DAADA56547_");
		m1.setName("med");
		m1.setWeight(150);
		m1.setDrone(d1);
		d1.setMedications(List.of(m1));
		
		droneRepository.save(d1);
		d1.setId(null);
		d1.setBatteryCapacity(55);
		d1.setModel("Middleweight");
		d1.setSerialNumber("afad");
		d1.setWeightLimit(300);
		d1.setState("LOADED");
		m1.setCode("DAA6547_");
		m1.setName("med");
		m1.setWeight(300);
		m1.setDrone(d1);
		m1.setId(null);
		d1.setMedications(List.of(m1));
		
		droneRepository.save(d1);
	}

}
