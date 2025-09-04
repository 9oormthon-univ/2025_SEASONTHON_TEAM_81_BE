package com.goormthon.backend.mindwalk.domain.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormthon.backend.mindwalk.domain.auth.dto.AuthRequest;
import com.goormthon.backend.mindwalk.domain.auth.dto.AuthResponse;
import com.goormthon.backend.mindwalk.domain.garden.dao.GardenRepository;
import com.goormthon.backend.mindwalk.domain.garden.domain.Garden;
import com.goormthon.backend.mindwalk.domain.garden.domain.GardenPlant;
import com.goormthon.backend.mindwalk.domain.user.dao.UserRepository;
import com.goormthon.backend.mindwalk.domain.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtProvider jwtProvider;
	private final UserRepository userRepository;
	private final GardenRepository gardenRepository;

	@Transactional
	public AuthResponse login(AuthRequest request) {
		User user = findOrCreateUser(request.oauthId());
		return jwtProvider.issueToken(user.getId());
	}

	private User findOrCreateUser(Long oauthId) {
		return userRepository.findByOauthId(oauthId)
			.orElseGet(() -> {
				User newUser = User.createUser(oauthId);
				userRepository.save(newUser);
				Garden newGarden = Garden.createGarden(newUser);
				GardenPlant newPlant = GardenPlant.createGardenPlant(newGarden);
				newGarden.getGardenPlants().add(newPlant);
				gardenRepository.save(newGarden);
				return newUser;
			});
	}
}
