package com.goormthon.backend.mindwalk.domain.auth.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.auth.api.docs.AuthControllerDocs;
import com.goormthon.backend.mindwalk.domain.auth.application.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerDocs {

	private final AuthService authService;
}
