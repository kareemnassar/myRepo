package com.drones.schedule;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.drones.entity.History;
import com.drones.repository.DroneRepository;
import com.drones.repository.HistoryRepository;



@Service
public class Schedule {
	Logger log=LoggerFactory.getLogger(Schedule.class);
	@Autowired
	private DroneRepository droneRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Scheduled(cron = "*/10 * * * * *")//check every 10 sec
	public void checkBattery() {
		log.info("check Battery ");
	droneRepository.findAll().forEach(drone->{
		historyRepository.save(new History(drone.getId(),drone.getBatteryCapacity(),drone.getState()
				,LocalDateTime.now()));
	});
		
	}
}
