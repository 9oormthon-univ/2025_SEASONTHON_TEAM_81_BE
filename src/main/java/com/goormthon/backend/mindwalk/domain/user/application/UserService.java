package com.goormthon.backend.mindwalk.domain.user.application;

import org.springframework.stereotype.Service;

import com.goormthon.backend.mindwalk.domain.user.dao.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
}
