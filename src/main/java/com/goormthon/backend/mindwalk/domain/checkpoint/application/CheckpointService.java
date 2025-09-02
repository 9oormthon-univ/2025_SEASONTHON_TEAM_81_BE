package com.goormthon.backend.mindwalk.domain.checkpoint.application;

import org.springframework.stereotype.Service;

import com.goormthon.backend.mindwalk.domain.checkpoint.dao.CheckpointRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckpointService {

	private final CheckpointRepository checkpointRepository;
}
