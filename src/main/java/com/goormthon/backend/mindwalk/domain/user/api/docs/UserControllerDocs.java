package com.goormthon.backend.mindwalk.domain.user.api.docs;

import org.springframework.web.bind.annotation.RequestBody;

import com.goormthon.backend.mindwalk.domain.auth.presentation.annotation.AuthenticatedId;
import com.goormthon.backend.mindwalk.domain.user.dto.request.UserNicknameRequest;
import com.goormthon.backend.mindwalk.domain.user.dto.request.UserNotificationRequest;
import com.goormthon.backend.mindwalk.domain.user.dto.response.UserInfoResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "[2. 회원]", description = "회원 관련 API")
public interface UserControllerDocs {

	@Operation(
		summary = "회원 닉네임 등록",
		description = "사용자의 닉네임을 등록합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "닉네임 등록 성공"),
		@ApiResponse(responseCode = "400", description = "요청 본문이 비어있거나 닉네임 형식이 유효하지 않은 경우",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
	})
	BaseResponse<Void> createNickname(
		@Parameter(hidden = true) @AuthenticatedId Long userId,
		@Valid @RequestBody UserNicknameRequest request
	);

	@Operation(
		summary = "회원 닉네임 수정",
		description = "사용자의 닉네임을 수정합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "닉네임 수정 성공"),
		@ApiResponse(responseCode = "400", description = "요청 본문이 비어있거나 닉네임 형식이 유효하지 않은 경우",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
	})
	public BaseResponse<Void> updateNickname(@Parameter(hidden = true) @AuthenticatedId Long currentUserId,
		@RequestBody UserNicknameRequest request);

	@Operation(
		summary = "회원 정보 조회",
		description = "현재 로그인된 사용자의 정보를 조회합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "내 정보 조회 성공",
			content = @Content(schema = @Schema(implementation = UserInfoResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
		@ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음",
			content = @Content(schema = @Schema(implementation = BaseResponse.class)))
	})
	BaseResponse<UserInfoResponse> getUserInfo(
		@Parameter(hidden = true) @AuthenticatedId Long userId
	);

	@Operation(
		summary = "회원 알림 설정 수정",
		description = "사용자의 푸시 알림 등 설정을 수정합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "설정 수정 성공")
	})
	BaseResponse<Void> updateUser(@Parameter(hidden = true) @AuthenticatedId Long userId,
		@RequestBody UserNotificationRequest request);
}
