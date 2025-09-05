package com.goormthon.backend.mindwalk.domain.garden.domain;

import java.util.Random;

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
	private static final Random RANDOM = new Random();

	public static FlowerType getRandomFlowerType() {
		FlowerType[] flowerTypes = values();
		return flowerTypes[RANDOM.nextInt(flowerTypes.length)];
	}
}
