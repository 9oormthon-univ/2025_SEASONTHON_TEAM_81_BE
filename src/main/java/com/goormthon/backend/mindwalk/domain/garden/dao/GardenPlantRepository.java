package com.goormthon.backend.mindwalk.domain.garden.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormthon.backend.mindwalk.domain.garden.domain.GardenPlant;

@Repository
public interface GardenPlantRepository extends JpaRepository<GardenPlant, Long> {
}
