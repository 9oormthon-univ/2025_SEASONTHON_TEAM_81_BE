package com.goormthon.backend.mindwalk.domain.garden.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.garden.api.docs.GardenControllerDocs;
import com.goormthon.backend.mindwalk.domain.garden.application.GardenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gardens")
public class GardenController implements GardenControllerDocs {

	private final GardenService gardenService;
}
