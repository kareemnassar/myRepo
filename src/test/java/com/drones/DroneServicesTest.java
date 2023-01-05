package com.drones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.drones.entity.Drone;
import com.drones.service.DroneService;

@SpringBootTest
public class DroneServicesTest {
	@Autowired
	private DroneService droneService;
	@Test    // these are 2 tests just as examples and we can add more test cases but sorry for time
	public void findByIdTest() {
		
		Drone d=droneService.getById(1L);
		assertEquals(false, d.equals(null));
		assertEquals(1, d.getId());

	}
	
	@Test
	public void findAllTest() {
		
		List<Drone> list=droneService.getAllDrones();
		assertEquals(false, list.isEmpty());
		
	}
}
