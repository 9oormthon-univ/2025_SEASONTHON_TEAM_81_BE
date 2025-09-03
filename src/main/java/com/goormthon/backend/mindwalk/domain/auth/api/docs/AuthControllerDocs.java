package com.goormthon.backend.mindwalk.domain.auth.api.docs;

import com.goormthon.backend.mindwalk.domain.auth.dto.AuthRequest;
import com.goormthon.backend.mindwalk.domain.auth.dto.AuthResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[1. 인증]", description = "인증 관련 API")
public interface AuthControllerDocs {

	@Operation(
		summary = "카카오 로그인",
		description = "카카오 소셜 로그인을 통해 사용자를 인증하고, 신규 사용자인 경우 자동으로 가입 처리 후 토큰을 발급합니다."
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "로그인 성공 및 토큰 발급",
			content = @Content(schema = @Schema(implementation = AuthResponse.class))),
		@ApiResponse(responseCode = "400", description = "요청 본문이 비어있거나 oauthId가 유효하지 않은 경우",
			content = @Content(schema = @Schema(implementation = BaseResponse.class))),
		@ApiResponse(responseCode = "500", description = "서버 내부 오류",
			content = @Content(schema = @Schema(implementation = BaseResponse.class)))
	})
	public BaseResponse<AuthResponse> login(AuthRequest request);
}
