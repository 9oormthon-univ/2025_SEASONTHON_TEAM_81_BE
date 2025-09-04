package com.goormthon.backend.mindwalk.domain.walkmission.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.auth.presentation.annotation.AuthenticatedId;
import com.goormthon.backend.mindwalk.domain.walkmission.api.docs.WalkMissionControllerDocs;
import com.goormthon.backend.mindwalk.domain.walkmission.application.WalkMissionService;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.request.WalkMissionCreateRequest;
import com.goormthon.backend.mindwalk.domain.walkmission.dto.response.WalkMissionListResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class WalkMissionController implements WalkMissionControllerDocs {

	private final WalkMissionService walkMissionService;

	@PostMapping
	public BaseResponse<WalkMissionListResponse> createWalkMission(
		@AuthenticatedId Long currentUserId,
		@RequestBody WalkMissionCreateRequest request) {
		return BaseResponse.success(walkMissionService.createWalkMission(currentUserId, request));
	}

	@PostMapping("/{missionId}/cancle")
	public BaseResponse<Void> cancelWalkMission(@AuthenticatedId Long currentUserId,
		@PathVariable(value = "missionId") Long missionId) {
		walkMissionService.cancelWalkMission(currentUserId, missionId);
		return BaseResponse.success();
	}
}
