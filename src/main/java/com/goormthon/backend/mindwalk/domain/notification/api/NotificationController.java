package com.goormthon.backend.mindwalk.domain.notification.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.notification.api.docs.NotificationApi;
import com.goormthon.backend.mindwalk.domain.notification.application.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController implements NotificationApi {

	private final NotificationService notificationService;
}
