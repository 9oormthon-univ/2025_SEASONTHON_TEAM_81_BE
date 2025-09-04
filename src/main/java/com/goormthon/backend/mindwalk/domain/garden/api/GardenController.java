package com.goormthon.backend.mindwalk.domain.garden.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.auth.presentation.annotation.AuthenticatedId;
import com.goormthon.backend.mindwalk.domain.garden.api.docs.GardenControllerDocs;
import com.goormthon.backend.mindwalk.domain.garden.application.GardenService;
import com.goormthon.backend.mindwalk.domain.garden.dto.response.GetGardenInfoResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gardens")
public class GardenController implements GardenControllerDocs {

	private final GardenService gardenService;

	@GetMapping
	public BaseResponse<GetGardenInfoResponse> getUserGarden(@AuthenticatedId Long currentUserId) {
		return BaseResponse.success(gardenService.getUserGarden(currentUserId));
	}
}
