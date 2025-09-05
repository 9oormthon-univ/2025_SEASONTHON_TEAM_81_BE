package com.goormthon.backend.mindwalk.domain.garden.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormthon.backend.mindwalk.domain.garden.domain.Garden;
import com.goormthon.backend.mindwalk.domain.garden.domain.GardenPlant;
import com.goormthon.backend.mindwalk.domain.garden.domain.PlantStage;

@Repository
public interface GardenPlantRepository extends JpaRepository<GardenPlant, Long> {
	Optional<GardenPlant> findByGardenAndPlantStageNot(Garden garden, PlantStage plantStage);

	Long countByGardenAndPlantStage(Garden garden, PlantStage plantStage);
}
