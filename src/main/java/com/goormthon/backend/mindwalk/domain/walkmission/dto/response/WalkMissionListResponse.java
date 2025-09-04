package com.goormthon.backend.mindwalk.domain.walkmission.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMission;
import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.domain.HealingContent;

public record WalkMissionListResponse(
	Long missionId,
	List<WalkMissionInfoResponse> healingContents
) {
	public static WalkMissionListResponse from(WalkMission walkMission, List<HealingContent> healingContents) {
		List<WalkMissionInfoResponse> missionInfos = healingContents.stream()
			.map(WalkMissionInfoResponse::from)
			.collect(Collectors.toList());

		return new WalkMissionListResponse(
			walkMission.getId(),
			missionInfos
		);
	}
}
