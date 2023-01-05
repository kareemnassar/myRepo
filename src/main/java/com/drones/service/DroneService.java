package com.drones.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drones.dto.DroneDTO;
import com.drones.dto.LoadDroneDTO;
import com.drones.entity.Drone;
import com.drones.entity.Medication;
import com.drones.repository.DroneRepository;

import jakarta.validation.Valid;

@Service
public class DroneService {
	
	private final DroneRepository droneRepository;
	Logger log=LoggerFactory.getLogger(DroneService.class);
	@Autowired
	public DroneService(DroneRepository droneRepository) {
		super();
		this.droneRepository = droneRepository;
		
	}
	
	public List<Drone> getAllDrones(){
		log.info("get all drones");
		List<Drone> drones=	droneRepository.findAll();
		if (drones.isEmpty()) {
			log.error("No drones found");
			throw new IllegalStateException("No drones found");
		}
		return drones;
	}
	
	public List<Drone> getAvailableDrones(){
		log.info("get all availavle drones");
		List<Drone> drones=	droneRepository.findAvailableDrones();
		if (drones.isEmpty()) {
			log.error("No available drones to load");
			throw new IllegalStateException("No available drones to load");
		}
		return drones;
	}
	
	public String getDroneBatteryLevel(Long id) {
		String response=droneRepository.checkDroneBattery(id);
		if (response==null) {
			log.error("No drone found with id : {}",id);
			throw new IllegalStateException("No drone found with id : "+id);
		}
		return response+"%";
	}
	
	public DroneDTO addDrone(DroneDTO dto) {
		
		if(dto.getId()==null) {
			//mapping from dto to entity  we can use map struct instead
			log.info("add new drone");
			log.info("mapping from dto to entity");
			Drone drone=new Drone();
			drone.setId(dto.getId());
			drone.setBatteryCapacity(dto.getBatteryCapacity());
			drone.setModel(dto.getModel());
			drone.setSerialNumber(dto.getSerialNumber());
			drone.setWeightLimit(dto.getWeightLimit());
			
		Drone insertedDrone= droneRepository.save(drone);
		//mapping from entity to dto
				log.info("mapping from entity to dto");
				DroneDTO droneDTO=new DroneDTO();
				droneDTO.setBatteryCapacity( insertedDrone.getBatteryCapacity());
				droneDTO.setId(insertedDrone.getId());
				droneDTO.setSerialNumber(insertedDrone.getSerialNumber());
				droneDTO.setModel(insertedDrone.getModel());
				droneDTO.setWeightLimit(insertedDrone.getWeightLimit());
				droneDTO.setCreatedBy(insertedDrone.getCreatedBy());
				droneDTO.setCreatedDate(insertedDrone.getCreatedDate());
				droneDTO.setLastModifiedBy(insertedDrone.getLastModifiedBy());
				droneDTO.setLastModifiedDate(insertedDrone.getLastModifiedDate());
				log.info("save drone");
				return droneDTO;
		}else {
			log.error("ID must be null ,drone exists with id : {}",dto.getId());
			throw new IllegalStateException("ID must be null");
		}
	}

	public DroneDTO updateDrone(@Valid DroneDTO dto) {
		if(dto.getId()==null) {
			log.error("ID can not be null ");
			throw new IllegalStateException("ID can not be null");
		
		}else {
			log.info("update drone with id {}",dto.getId());
			
			Drone drone=getById(dto.getId());
			drone.setId(dto.getId());
			drone.setBatteryCapacity(dto.getBatteryCapacity());
			drone.setModel(dto.getModel());
			drone.setSerialNumber(dto.getSerialNumber());
			drone.setWeightLimit(dto.getWeightLimit());
			Drone insertedDrone =droneRepository.save(drone);
			//mapping from entity to dto
			DroneDTO droneDTO=new DroneDTO();
			droneDTO.setBatteryCapacity( insertedDrone.getBatteryCapacity());
			droneDTO.setId(insertedDrone.getId());
			droneDTO.setSerialNumber(insertedDrone.getSerialNumber());
			droneDTO.setModel(insertedDrone.getModel());
			droneDTO.setWeightLimit(insertedDrone.getWeightLimit());
			droneDTO.setCreatedBy(insertedDrone.getCreatedBy());
			droneDTO.setCreatedDate(insertedDrone.getCreatedDate());
			droneDTO.setLastModifiedBy(insertedDrone.getLastModifiedBy());
			droneDTO.setLastModifiedDate(insertedDrone.getLastModifiedDate());
			return droneDTO;
		}
	}

	public List<Medication> checkLodedDrone(Long id) {
		log.info("check loaded drone");
		List<Medication> medications=	droneRepository.findLoadedDrone(id);
		if (medications.isEmpty()) {
			log.error("No drone found with id {} ",id);
			throw new IllegalStateException("No drone found with id : "+id);
		}
		return medications;
	
	}
	int weight=0;
	byte [] pic=null;
	public LoadDroneDTO LoadDrone(@Valid LoadDroneDTO dto)  {
		log.info("Load drone");
	
		Drone drone=getById(dto.getId());
		
		weight=0;
		log.info("loading drone with id {}",dto.getId());
		log.info("check if battery < 25%");
		if(drone.getBatteryCapacity()<25) {
			log.error("Can not load drone with battery under 25%");
			throw new IllegalStateException("Can not load drone with battery under 25%");
		}
		log.info("check id");
		if(dto.getId()==null) {
			log.error("ID can not be null");
			throw new IllegalStateException("ID can not be null");
		}
		log.info("check medication list");
		if(dto.getMedications().isEmpty()){
			log.error("No medications to load");
			throw new IllegalStateException("No medications to load");
			
		}else {
			drone.setId(dto.getId());
			drone.setState("LOADING");
			drone.getMedications().addAll(dto.getMedications());
			drone.getMedications().forEach(element->{element.setDrone(drone);});
			log.info("check medication weight if it exceed limit");
			dto.getMedications().forEach(med->{
				weight+=med.getWeight();
			});
			if(weight>drone.getWeightLimit()) {
				log.error("loaded weight exceed limit weight for this drone");
			throw new IllegalStateException("loaded weight exceed limit weight for this drone");
			}
			log.info("check medication weight if = WeightLimit");
			if(weight==drone.getWeightLimit()) {
				log.info("change state to LOADED");
				drone.setState("LOADED");
			}
		} 
	Drone insertedDrone=	droneRepository.save(drone);
		LoadDroneDTO droneDTO=new LoadDroneDTO();
		droneDTO.setMedications( insertedDrone.getMedications());
		droneDTO.setId(insertedDrone.getId());
		droneDTO.setCreatedBy(insertedDrone.getCreatedBy());
		droneDTO.setCreatedDate(insertedDrone.getCreatedDate());
		droneDTO.setLastModifiedBy(insertedDrone.getLastModifiedBy());
		droneDTO.setLastModifiedDate(insertedDrone.getLastModifiedDate());
			return droneDTO;
		
	}
	
	public Drone getById(Long id) {
		log.info("get drone by id {}",id);
		Optional<Drone> drone=	droneRepository.findById(id);
				if(drone.isEmpty()) {
					log.error("No drone found with id {}",id);
					throw new IllegalStateException("No drone found with id : "+id);
				}
		
		return drone.get();
	}
	public String deleteDrone(Long id) {
		droneRepository.deleteById(id);
		
		return "Drone with id : "+id+" deleted successfully";
		
	}
	

}
