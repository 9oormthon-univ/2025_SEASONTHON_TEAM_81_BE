package com.goormthon.backend.mindwalk.domain.garden.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormthon.backend.mindwalk.domain.garden.dao.GardenRepository;
import com.goormthon.backend.mindwalk.domain.garden.domain.Garden;
import com.goormthon.backend.mindwalk.domain.garden.domain.GardenPlant;
import com.goormthon.backend.mindwalk.domain.garden.domain.PlantStage;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGardenInfoResponse;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGardenInfoResponse.TodayGrowth;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGardenInfoResponse.UserFlower;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGardenInfoResponse.UserGardenPlant;
import com.goormthon.backend.mindwalk.global.exception.BaseException;
import com.goormthon.backend.mindwalk.global.exception.BaseResponseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GardenService {

	private final GardenRepository gardenRepository;

	@Transactional(readOnly = true)
	public GetGardenInfoResponse getUserGarden(Long userId) {
		Garden garden = gardenRepository.findByUserId(userId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_GARDEN));
		List<GardenPlant> gardenPlants = garden.getGardenPlants();
		TodayGrowth todayGrowth = createTodayGrowth(gardenPlants);
		List<UserGardenPlant> userGardenPlants = createUserGardenPlants(gardenPlants);
		List<UserFlower> flowerCollection = createFlowerCollection(gardenPlants);
		return new GetGardenInfoResponse(todayGrowth, userGardenPlants, flowerCollection);
	}

	// TODO: 요구사항 확인 후 작업 예정
	private TodayGrowth createTodayGrowth(List<GardenPlant> gardenPlants) {
		return null;
	}

	private List<UserGardenPlant> createUserGardenPlants(List<GardenPlant> gardenPlants) {
		return gardenPlants.stream()
			.map(UserGardenPlant::from)
			.toList();
	}

	private List<UserFlower> createFlowerCollection(List<GardenPlant> gardenPlants) {
		return gardenPlants.stream()
			.filter(gardenPlant -> gardenPlant.getPlantStage() == PlantStage.FLOWER)
			.map(UserFlower::from)
			.toList();
	}
}
