package com.goormthon.backend.mindwalk.domain.user.dto.request;

public record UserNotificationRequest(
	boolean allowPushNotification
) {
}
