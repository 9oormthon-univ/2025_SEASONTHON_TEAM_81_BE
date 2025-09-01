package com.goormthon.backend.mindwalk.domain.walkmission.application;

import org.springframework.stereotype.Service;

import com.goormthon.backend.mindwalk.domain.walkmission.dao.WalkMissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalkMissionService {

	private final WalkMissionRepository walkMissionRepository;
}
