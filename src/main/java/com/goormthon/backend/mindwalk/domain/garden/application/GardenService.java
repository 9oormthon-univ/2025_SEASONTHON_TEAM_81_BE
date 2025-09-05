package com.goormthon.backend.mindwalk.domain.garden.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormthon.backend.mindwalk.domain.garden.dao.GardenPlantRepository;
import com.goormthon.backend.mindwalk.domain.garden.dao.GardenRepository;
import com.goormthon.backend.mindwalk.domain.garden.domain.Garden;
import com.goormthon.backend.mindwalk.domain.garden.domain.GardenPlant;
import com.goormthon.backend.mindwalk.domain.garden.domain.PlantStage;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGardenInfoResponse;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGardenInfoResponse.TodayGrowth;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGardenInfoResponse.UserFlower;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGrowingPlantInfoResponse;
import com.goormthon.backend.mindwalk.domain.user.domain.User;
import com.goormthon.backend.mindwalk.domain.walkmission.dao.WalkMissionRepository;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMission;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMissionStatus;
import com.goormthon.backend.mindwalk.global.exception.BaseException;
import com.goormthon.backend.mindwalk.global.exception.BaseResponseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GardenService {

	private final GardenRepository gardenRepository;
	private final GardenPlantRepository gardenPlantRepository;
	private final WalkMissionRepository walkMissionRepository;

	@Transactional(readOnly = true)
	public GetGardenInfoResponse getUserGarden(Long userId) {
		Garden garden = gardenRepository.findByUserId(userId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_GARDEN));
		List<GardenPlant> gardenPlants = garden.getGardenPlants();
		TodayGrowth todayGrowth = createTodayGrowth(garden.getUser(), gardenPlants);
		List<UserFlower> flowerCollection = createFlowerCollection(gardenPlants);
		return new GetGardenInfoResponse(todayGrowth, flowerCollection);
	}

	private TodayGrowth createTodayGrowth(User user, List<GardenPlant> gardenPlants) {
		if (gardenPlants.isEmpty()) {
			return null;
		}
		GardenPlant userGrowingPlant = gardenPlants.stream()
			.max(Comparator.comparing(GardenPlant::getId))
			.get();
		LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
		LocalDateTime endOfToday = startOfToday.plusDays(1);
		List<WalkMission> completedMissionsToday = walkMissionRepository.findAllByUserAndStatusAndCompletedAtBetween(
			user, WalkMissionStatus.COMPLETED, startOfToday, endOfToday);
		Long todayGrowthPoint = (long)completedMissionsToday.size() * 10;
		return TodayGrowth.of(todayGrowthPoint, userGrowingPlant);
	}

	private List<UserFlower> createFlowerCollection(List<GardenPlant> gardenPlants) {
		return gardenPlants.stream()
			.filter(gardenPlant -> gardenPlant.getPlantStage() == PlantStage.FLOWER)
			.map(UserFlower::from)
			.toList();
	}

	@Transactional(readOnly = true)
	public GetGrowingPlantInfoResponse getUserGrowingPlant(Long userId) {
		Garden garden = gardenRepository.findByUserId(userId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_GARDEN));
		List<GardenPlant> gardenPlants = garden.getGardenPlants();
		GardenPlant userGrowingPlant = gardenPlants.stream()
			.max(Comparator.comparing(GardenPlant::getId))
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_GROWING_PLANT));
		long plantSequence = gardenPlants.size();
		return GetGrowingPlantInfoResponse.of(userGrowingPlant, plantSequence);
	}

	public void addGrowthPointAfterMission(Long userId) {
		Garden garden = gardenRepository.findByUserId(userId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_GARDEN));
		Optional<GardenPlant> growingPlantOpt = gardenPlantRepository.findByGardenAndPlantStageNot(garden,
			PlantStage.FLOWER);
		GardenPlant plantToGrow = growingPlantOpt.orElseGet(() -> createNewPlant(garden));
		plantToGrow.addGrowthPoint(10L);
	}

	private GardenPlant createNewPlant(Garden garden) {
		GardenPlant newPlant = GardenPlant.createGardenPlant(garden);
		return gardenPlantRepository.save(newPlant);
	}
}
