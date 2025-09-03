package com.goormthon.backend.mindwalk.domain.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.auth.presentation.annotation.AuthenticatedId;
import com.goormthon.backend.mindwalk.domain.user.api.docs.UserControllerDocs;
import com.goormthon.backend.mindwalk.domain.user.application.UserService;
import com.goormthon.backend.mindwalk.domain.user.dto.request.UserNicknameRequest;
import com.goormthon.backend.mindwalk.domain.user.dto.request.UserNotificationRequest;
import com.goormthon.backend.mindwalk.domain.user.dto.response.UserInfoResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController implements UserControllerDocs {

	private final UserService userService;

	@PostMapping("/nickname")
	public BaseResponse<Void> createNickname(@Parameter(hidden = true) @AuthenticatedId Long currentUserId,
		@RequestBody UserNicknameRequest request) {
		userService.assignNickname(currentUserId, request);
		return BaseResponse.success();
	}

	@PutMapping("/nickname")
	public BaseResponse<Void> updateNickname(@Parameter(hidden = true) @AuthenticatedId Long currentUserId,
		@RequestBody UserNicknameRequest request) {
		userService.assignNickname(currentUserId, request);
		return BaseResponse.success();
	}

	@GetMapping("/me")
	public BaseResponse<UserInfoResponse> getUserInfo(@Parameter(hidden = true) @AuthenticatedId Long currentUserId) {
		return BaseResponse.success(userService.getUserInfo(currentUserId));
	}

	@PutMapping("/me")
	public BaseResponse<Void> updateUser(@Parameter(hidden = true) @AuthenticatedId Long currentUserId,
		@RequestBody UserNotificationRequest request) {
		userService.updateUser(currentUserId, request);
		return BaseResponse.success();
	}
}
