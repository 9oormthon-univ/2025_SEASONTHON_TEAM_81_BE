package com.goormthon.backend.mindwalk.domain.garden.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlantStage {
	SEED("씨앗"),
	SPROUT("새싹"),
	FLOWER("꽃");

	private final String value;
}
