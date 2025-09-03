package com.goormthon.backend.mindwalk.domain.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormthon.backend.mindwalk.domain.auth.dto.AuthRequest;
import com.goormthon.backend.mindwalk.domain.auth.dto.AuthResponse;
import com.goormthon.backend.mindwalk.domain.user.dao.UserRepository;
import com.goormthon.backend.mindwalk.domain.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtProvider jwtProvider;
	private final UserRepository userRepository;

	@Transactional
	public AuthResponse login(AuthRequest request) {
		User user = findOrCreateUser(request.oauthId());
		return jwtProvider.issueToken(user.getId());
	}

	private User findOrCreateUser(Long oauthId) {
		return userRepository.findByOauthId(oauthId)
			.orElseGet(() -> {
				User newUser = User.builder()
					.oauthId(oauthId)
					.build();
				return userRepository.save(newUser);
			});
	}
}
