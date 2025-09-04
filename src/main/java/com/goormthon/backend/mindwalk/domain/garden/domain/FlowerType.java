package com.goormthon.backend.mindwalk.domain.garden.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FlowerType {
	ROSE("장미"),
	SUNFLOWER("해바라기"),
	LILY("백합"),
	LAVENDER("라벤더");

	private final String value;
}
