package com.goormthon.backend.mindwalk.domain.garden.dto.response;

import java.util.List;

import com.goormthon.backend.mindwalk.domain.garden.domain.GardenPlant;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetGardenInfoResponse(
	TodayGrowth todayGrowth,
	List<UserGardenPlant> gardenPlants,
	List<UserFlower> flowerCollection
) {
	public record TodayGrowth(
		@Schema(description = "오늘 하루 성장 포인트", example = "10")
		Long todayGrowthPoint,
		@Schema(description = "현재 식물 성장 단계", example = "씨앗")
		String plantStage,
		@Schema(description = "꽃 종류", example = "장미")
		String flowerType
	) {
	}

	public record UserGardenPlant(
		@Schema(description = "정원 식물 ID", example = "1")
		Long gardenPlantId,
		@Schema(description = "식물 성장 단계", example = "씨앗")
		String plantStage,
		@Schema(description = "식물 종류", example = "장미")
		String flowerType
	) {
		public static UserGardenPlant from(GardenPlant gardenPlant) {
			return new UserGardenPlant(
				gardenPlant.getId(),
				gardenPlant.getPlantStage().getValue(),
				gardenPlant.getFlowerType().getValue()
			);
		}
	}

	public record UserFlower(
		@Schema(description = "수집한 꽃 ID", example = "2")
		Long gardenPlantId,
		@Schema(description = "꽃 종류", example = "해바라기")
		String flowerType
	) {
		public static UserFlower from(GardenPlant gardenPlant) {
			return new UserFlower(
				gardenPlant.getId(),
				gardenPlant.getFlowerType().getValue()
			);
		}
	}
}
