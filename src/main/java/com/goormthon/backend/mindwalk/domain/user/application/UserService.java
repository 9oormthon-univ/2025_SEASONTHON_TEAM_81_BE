package com.goormthon.backend.mindwalk.domain.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormthon.backend.mindwalk.domain.garden.dao.GardenPlantRepository;
import com.goormthon.backend.mindwalk.domain.garden.dao.GardenRepository;
import com.goormthon.backend.mindwalk.domain.garden.domain.Garden;
import com.goormthon.backend.mindwalk.domain.garden.domain.PlantStage;
import com.goormthon.backend.mindwalk.domain.user.dao.UserRepository;
import com.goormthon.backend.mindwalk.domain.user.domain.User;
import com.goormthon.backend.mindwalk.domain.user.dto.request.UserNicknameRequest;
import com.goormthon.backend.mindwalk.domain.user.dto.request.UserNotificationRequest;
import com.goormthon.backend.mindwalk.domain.user.dto.response.UserInfoResponse;
import com.goormthon.backend.mindwalk.domain.walkmission.dao.WalkMissionRepository;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMissionStatus;
import com.goormthon.backend.mindwalk.global.exception.BaseException;
import com.goormthon.backend.mindwalk.global.exception.BaseResponseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final WalkMissionRepository walkMissionRepository;
	private final GardenRepository gardenRepository;
	private final GardenPlantRepository gardenPlantRepository;

	@Transactional
	public void assignNickname(Long currentUserId, UserNicknameRequest request) {
		User user = findUserById(currentUserId);
		user.updateNickname(request.nickname());
	}

	@Transactional(readOnly = true)
	public UserInfoResponse getUserInfo(Long currentUserId) {
		User user = findUserById(currentUserId);
		Long totalWalkCount = walkMissionRepository.countByUserAndStatus(user, WalkMissionStatus.COMPLETED);
		Garden garden = gardenRepository.findByUserId(currentUserId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_GARDEN));
		Long bloomedFlowerCount = gardenPlantRepository.countByGardenAndPlantStage(garden, PlantStage.FLOWER);
		return UserInfoResponse.of(user, totalWalkCount, bloomedFlowerCount);
	}

	@Transactional
	public void updateUser(Long currentUserId, UserNotificationRequest request) {
		User user = findUserById(currentUserId);
		// TODO: 알림 설정 업데이트 로직 구현
		// user.updateNorification(request.allow_push_notification());
	}

	private User findUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
	}
}
