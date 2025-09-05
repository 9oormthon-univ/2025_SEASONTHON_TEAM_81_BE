package com.goormthon.backend.mindwalk.domain.garden.dto.response;

import com.goormthon.backend.mindwalk.domain.garden.domain.GardenPlant;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetGrowingPlantInfoResponse(
	@Schema(description = "식물 고유 ID", example = "5")
	Long gardenPlantId,
	@Schema(description = "사용자가 현재 키우고 있는 식물이 몇 번째 식물인지", example = "5")
	Long plantSequence,
	@Schema(description = "꽃 종류", example = "해바라기")
	String flowerType,
	@Schema(description = "사용자가 현재 키우고 있는 식물의 누적 성장 포인트", example = "80")
	Long growthPoint,
	@Schema(description = "현재 식물 단계", example = "새싹")
	String plantStage
) {
	public static GetGrowingPlantInfoResponse of(GardenPlant plant, Long plantSequence) {
		return new GetGrowingPlantInfoResponse(
			plant.getId(),
			plantSequence,
			plant.getFlowerType().getValue(),
			plant.getGrowthPoint(),
			plant.getPlantStage().getValue()
		);
	}
}
