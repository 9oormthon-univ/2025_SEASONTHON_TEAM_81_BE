package com.goormthon.backend.mindwalk.domain.walkmission.application;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormthon.backend.mindwalk.domain.user.dao.UserRepository;
import com.goormthon.backend.mindwalk.domain.user.domain.User;
import com.goormthon.backend.mindwalk.domain.walkmission.dao.WalkMissionRepository;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMission;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMissionStatus;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.request.WalkMissionCreateRequest;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.response.WalkMissionListResponse;
import com.goormthon.backend.mindwalk.domain.walkmission.healingContent.dao.HealingContentRepository;
import com.goormthon.backend.mindwalk.domain.walkmission.healingContent.dao.WalkMissionHealingContentRepository;
import com.goormthon.backend.mindwalk.domain.walkmission.healingContent.domain.HealingContent;
import com.goormthon.backend.mindwalk.domain.walkmission.healingContent.domain.WalkMissionHealingContent;
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

	@Transactional
	public WalkMissionListResponse createWalkMission(Long currentUserId, WalkMissionCreateRequest request) {
		User user = findUserById(currentUserId);

		WalkMission walkMission = WalkMission.builder()
			.targetDurationMinutes(request.targetDurationMinutes())
			.status(WalkMissionStatus.IN_PROGRESS)
			.user(user)
			.build();
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
	public void cancelWalkMission(Long currentUserId, Long missionId) {
		User user = findUserById(currentUserId);
		WalkMission walkMission = walkMissionRepository.findById(missionId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MISSION));
		if (!walkMission.getUser().getId().equals(user.getId())) {
			throw new BaseException(BaseResponseStatus.FORBIDDEN);
		}

		walkMission.cancel();
	}

	private User findUserById(Long userId) {
		return userRepository.findById(userId)
			// TODO: NOT_FOUND_USER로 변경
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND));
	}
}
