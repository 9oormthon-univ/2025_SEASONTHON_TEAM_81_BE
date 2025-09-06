package com.goormthon.backend.mindwalk.domain.garden.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goormthon.backend.mindwalk.domain.garden.domain.GardenPlant;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetGardenInfoResponse(
	TodayGrowth todayGrowth,
	List<UserFlower> flowerCollection
) {
	public record TodayGrowth(
		@Schema(description = "오늘 하루 동안 획득한 식물 성장 포인트", example = "10")
		Long todayGrowthPoint,
		@Schema(description = "식물 ID", example = "5")
		Long gardenPlantId,
		@Schema(description = "사용자가 현재 키우고 있는 식물의 누적 성장 포인트", example = "50")
		Long growthPoint,
		@Schema(description = "현재 식물 성장 단계", example = "새싹")
		String plantStage,
		@Schema(description = "꽃 종류", example = "장미")
		String flowerType
	) {
		public static TodayGrowth of(Long todayGrowthPoint, GardenPlant gardenPlant) {
			return new TodayGrowth(
				todayGrowthPoint,
				gardenPlant.getId(),
				gardenPlant.getGrowthPoint(),
				gardenPlant.getPlantStage().getValue(),
				gardenPlant.getFlowerType().getValue()
			);
		}
	}

	public record UserFlower(
		@Schema(description = "수집한 꽃 ID", example = "2")
		Long gardenPlantId,
		@Schema(description = "꽃 종류", example = "해바라기")
		String flowerType,
		@Schema(description = "꽃이 핀 날짜", example = "2025-09-06")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		LocalDateTime bloomedAt
	) {
		public static UserFlower from(GardenPlant gardenPlant) {
			return new UserFlower(
				gardenPlant.getId(),
				gardenPlant.getFlowerType().getValue(),
				gardenPlant.getBloomedAt()
			);
		}
	}
}
