package com.goormthon.backend.mindwalk.domain.auth.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.auth.api.docs.AuthControllerDocs;
import com.goormthon.backend.mindwalk.domain.auth.application.AuthService;
import com.goormthon.backend.mindwalk.domain.auth.dto.AuthRequest;
import com.goormthon.backend.mindwalk.domain.auth.dto.AuthResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerDocs {

	private final AuthService authService;

	@PostMapping("/login")
	public BaseResponse<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		return BaseResponse.success(authService.login(request));
	}
}
