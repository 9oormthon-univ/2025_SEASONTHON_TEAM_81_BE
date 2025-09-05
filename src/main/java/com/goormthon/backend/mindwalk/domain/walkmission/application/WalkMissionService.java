package com.goormthon.backend.mindwalk.domain.walkmission.application;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormthon.backend.mindwalk.domain.garden.application.GardenService;
import com.goormthon.backend.mindwalk.domain.user.dao.UserRepository;
import com.goormthon.backend.mindwalk.domain.user.domain.User;
import com.goormthon.backend.mindwalk.domain.walkmission.dao.WalkMissionRepository;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMission;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.request.CompleteWalkMissionRequest;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.request.CreateWalkMissionRequest;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.response.WalkMissionListResponse;
import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.dao.HealingContentRepository;
import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.dao.WalkMissionHealingContentRepository;
import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.domain.HealingContent;
import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.domain.WalkMissionHealingContent;
import com.goormthon.backend.mindwalk.global.exception.BaseException;
import com.goormthon.backend.mindwalk.global.exception.BaseResponseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalkMissionService {

	private final UserRepository userRepository;
	private final WalkMissionRepository walkMissionRepository;
	private final HealingContentRepository healingContentRepository;
	private final WalkMissionHealingContentRepository walkMissionHealingContentRepository;
	private final GardenService gardenService;

	@Transactional
	public WalkMissionListResponse createWalkMission(Long currentUserId, CreateWalkMissionRequest request) {
		User user = findUserById(currentUserId);
		WalkMission walkMission = WalkMission.createWalkMission(user, request.targetDurationMinutes());
		walkMissionRepository.save(walkMission);
		List<HealingContent> healingContents = healingContentRepository.findByCategoryIn(request.healingContents());
		if (healingContents.isEmpty()) {
			throw new BaseException(BaseResponseStatus.NOT_FOUND_HEALING_CONTENT);
		}
		Collections.shuffle(healingContents);
		List<WalkMissionHealingContent> walkMissionHealingContents = healingContents.stream().map(healingContent ->
			WalkMissionHealingContent.builder()
				.walkMission(walkMission)
				.healingContent(healingContent)
				.build()
		).toList();
		walkMissionHealingContentRepository.saveAll(walkMissionHealingContents);
		return WalkMissionListResponse.from(walkMission, healingContents);
	}

	@Transactional
	public void cancelWalkMission(Long userId, Long missionId) {
		WalkMission walkMission = walkMissionRepository.findById(missionId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_WALK_MISSION));
		if (!walkMission.getUser().getId().equals(userId)) {
			throw new BaseException(BaseResponseStatus.FORBIDDEN);
		}
		walkMission.cancel();
	}

	private User findUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
	}

	@Transactional
	public void completeWalkMission(Long userId, Long missionId, CompleteWalkMissionRequest request) {
		WalkMission walkMission = walkMissionRepository.findById(missionId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_WALK_MISSION));
		if (!walkMission.getUser().getId().equals(userId)) {
			throw new BaseException(BaseResponseStatus.FORBIDDEN);
		}
		walkMission.complete(request.steps(), request.distanceMeters(), request.actualDurationMinutes());
		gardenService.addGrowthPointAfterMission(userId);
	}
}
