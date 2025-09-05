package com.goormthon.backend.mindwalk.domain.user.dto.response;

import com.goormthon.backend.mindwalk.domain.user.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserInfoResponse(
	@Schema(description = "사용자 ID", example = "1")
	Long userId,
	@Schema(description = "닉네임", example = "닉네임")
	String nickname,
	@Schema(description = "마음 산책한 날", example = "35")
	Long totalWalkCount,
	@Schema(description = "피운 꽃 개수", example = "100")
	Long bloomedFlowerCount
) {
	public static UserInfoResponse of(User user, Long totalWalkCount, Long bloomedFlowerCount) {
		return new UserInfoResponse(
			user.getId(),
			user.getNickname(),
			totalWalkCount,
			bloomedFlowerCount
		);
	}
}
