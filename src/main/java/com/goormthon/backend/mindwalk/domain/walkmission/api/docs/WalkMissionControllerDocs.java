package com.goormthon.backend.mindwalk.domain.walkmission.api.docs;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.goormthon.backend.mindwalk.domain.auth.presentation.annotation.AuthenticatedId;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.request.CompleteWalkMissionRequest;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.request.CreateWalkMissionRequest;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.response.WalkMissionListResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "[3. 산책 미션]", description = "산책 미션 관련 API")
public interface WalkMissionControllerDocs {

	@Operation(
		summary = "산책 미션 생성 및 시작",
		description = "산책 미션을 생성하고, 선택된 카테고리에 해당하는 힐링 컨텐츠 목록을 함께 반환합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "산책 미션 생성 성공",
			content = @Content(schema = @Schema(implementation = WalkMissionListResponse.class))),
		@ApiResponse(responseCode = "400", description = "요청 본문이 비어있거나 유효하지 않은 경우",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
		@ApiResponse(responseCode = "404", description = "선택한 카테고리에 해당하는 힐링 컨텐츠가 없는 경우",
			content = @Content(schema = @Schema(implementation = BaseResponse.class)))
	})
	public BaseResponse<WalkMissionListResponse> createWalkMission(
		@Parameter(hidden = true) @AuthenticatedId Long currentUserId,
		@Valid @RequestBody CreateWalkMissionRequest request
	);

	@Operation(
		summary = "산책 미션 취소",
		description = "진행 중인 산책 미션을 취소합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "산책 미션 취소 성공"),
		@ApiResponse(responseCode = "400", description = "이미 완료되었거나 취소된 미션인 경우",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
		@ApiResponse(responseCode = "403", description = "해당 미션을 취소할 권한이 없는 경우",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
		@ApiResponse(responseCode = "404", description = "존재하지 않는 산책 미션인 경우",
			content = @Content(schema = @Schema(implementation = BaseResponse.class)))
	})
	public BaseResponse<Void> cancelWalkMission(
		@Parameter(hidden = true) @AuthenticatedId Long currentUserId,
		@PathVariable(value = "missionId") Long missionId
	);

	@Operation(
		summary = "산책 미션 완료",
		description = "진행 중인 산책 미션을 완료 처리합니다. 미션 완료 시 식물 성장 포인트가 10 상승합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "산책 미션 완료 성공")
	})
	public BaseResponse<Void> completeWalkMission(
		@Parameter(hidden = true) @AuthenticatedId Long currentUserId,
		@Parameter(description = "산책 미션 ID") @PathVariable(value = "missionId") Long missionId,
		@Valid @RequestBody CompleteWalkMissionRequest request
	);

	@Operation(
		summary = "산책 미션 완료",
		description = "진행 중인 산책 미션을 완료 처리합니다. 미션 완료 시 식물 성장 포인트가 10 상승합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "산책 미션 완료 성공")
	})
	public BaseResponse<Void> completeUserWalkMission(
		@Parameter(hidden = true) @AuthenticatedId Long currentUserId,
		@RequestBody CompleteWalkMissionRequest request
	);
}
