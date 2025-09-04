package com.goormthon.backend.mindwalk.domain.auth.dto;

import jakarta.validation.constraints.NotNull;

public record AuthRequest(
	@NotNull(message = "oauthId는 필수입니다.")
	Long oauthId
) {
}
