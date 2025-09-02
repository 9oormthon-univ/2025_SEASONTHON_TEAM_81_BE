package com.goormthon.backend.mindwalk.domain.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.user.api.docs.UserControllerDocs;
import com.goormthon.backend.mindwalk.domain.user.application.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController implements UserControllerDocs {

	private final UserService userService;
}
