package com.goormthon.backend.mindwalk.domain.report.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetWeeklySummaryResponse(
	@Schema(description = "어제 산책 성공 여부", example = "true")
	Boolean yesterdayWalkSuccess,
	@Schema(description = "이번 주 누적 산책 횟수", example = "5")
	Long weeklyWalkCount,
	@Schema(description = "이번 주 누적 산책 거리 (미터)", example = "12345")
	Long weeklyDistanceMeters,
	@Schema(description = "이번 주 누적 산책 시간 (분)", example = "120")
	Long weeklyDurationMinutes
) {
}
