package com.goormthon.backend.mindwalk.domain.checkpoint.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.checkpoint.api.docs.CheckpointControllerDocs;
import com.goormthon.backend.mindwalk.domain.checkpoint.application.CheckpointService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/checkpoint")
public class CheckpointController implements CheckpointControllerDocs {

	private final CheckpointService checkpointService;
}
