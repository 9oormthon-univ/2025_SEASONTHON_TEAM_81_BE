package com.goormthon.backend.mindwalk.domain.auth.dto;

public record AuthResponse(
	Long userId,
	String accessToken,
	String refreshToken
) {
}
