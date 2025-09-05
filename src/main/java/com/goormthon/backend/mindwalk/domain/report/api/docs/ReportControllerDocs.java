package com.goormthon.backend.mindwalk.domain.report.api.docs;

import com.goormthon.backend.mindwalk.domain.auth.presentation.annotation.AuthenticatedId;
import com.goormthon.backend.mindwalk.domain.report.dto.response.GetWeeklySummaryResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[5. 주간 요약]", description = "주간 요약 관련 API")
public interface ReportControllerDocs {

	@Operation(
		summary = "주간 요약 조회",
		description = "이번 주 산책 요약 정보를 조회합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "조회 성공",
			content = @Content(schema = @Schema(implementation = GetWeeklySummaryResponse.class))
		)
	})
	BaseResponse<GetWeeklySummaryResponse> getWeeklySummary(
		@Parameter(hidden = true) @AuthenticatedId Long currentUserId
	);
}
