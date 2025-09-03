package com.goormthon.backend.mindwalk.domain.user.dto.response;

import com.goormthon.backend.mindwalk.domain.user.domain.User;

public record UserInfoResponse(
	Long userId,
	String nickname
	// boolean allowPushNotification
) {
	public static UserInfoResponse from(User user) {
		return new UserInfoResponse(user.getId(), user.getNickname());
	}
}
