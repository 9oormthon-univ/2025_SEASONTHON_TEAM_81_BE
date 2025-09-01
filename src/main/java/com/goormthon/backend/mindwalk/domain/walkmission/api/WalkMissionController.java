package com.goormthon.backend.mindwalk.domain.walkmission.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.walkmission.api.docs.WalkMissionControllerDocs;
import com.goormthon.backend.mindwalk.domain.walkmission.application.WalkMissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class WalkMissionController implements WalkMissionControllerDocs {

	private final WalkMissionService walkMissionService;
}
