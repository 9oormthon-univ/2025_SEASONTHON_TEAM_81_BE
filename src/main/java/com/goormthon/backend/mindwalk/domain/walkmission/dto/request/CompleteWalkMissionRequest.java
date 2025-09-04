package com.goormthon.backend.mindwalk.domain.walkmission.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CompleteWalkMissionRequest(
	@Schema(description = "총 걸음 수", example = "800")
	Long steps,
	@Schema(description = "총 산책 이동 거리(미터 단위)", example = "1900")
	Long distanceMeters,
	@Schema(description = "총 산책 소요 시간(분 단위)", example = "17")
	Long actualDurationMinutes
) {
}
