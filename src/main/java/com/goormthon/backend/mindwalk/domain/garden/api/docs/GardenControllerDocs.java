package com.goormthon.backend.mindwalk.domain.garden.api.docs;

import com.goormthon.backend.mindwalk.domain.auth.presentation.annotation.AuthenticatedId;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGardenInfoResponse;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGrowingPlantInfoResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[5. 마음 정원]", description = "마음 정원 관련 API")
public interface GardenControllerDocs {

	@Operation(summary = "내 마음 정원 조회", description = "내 마음 정원을 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "조회 성공",
			content = @Content(schema = @Schema(implementation = GetGardenInfoResponse.class))
		)
	})
	BaseResponse<GetGardenInfoResponse> getUserGarden(
		@Parameter(hidden = true) @AuthenticatedId Long currentUserId
	);

	@Operation(summary = "사용자가 현재 키우고 있는 식물 조회", description = "사용자가 현재 키우고 있는 식물의 정보를 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "조회 성공",
			content = @Content(schema = @Schema(implementation = GetGrowingPlantInfoResponse.class))
		)
	})
	BaseResponse<GetGrowingPlantInfoResponse> getUserGrowingPlant(
		@Parameter(hidden = true) @AuthenticatedId Long currentUserId
	);
}
