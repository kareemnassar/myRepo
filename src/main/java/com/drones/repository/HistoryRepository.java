package com.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drones.entity.History;



public interface HistoryRepository  extends JpaRepository<History, Long>{ 

}
