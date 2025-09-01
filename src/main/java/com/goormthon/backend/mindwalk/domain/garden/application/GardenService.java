package com.goormthon.backend.mindwalk.domain.garden.application;

import org.springframework.stereotype.Service;

import com.goormthon.backend.mindwalk.domain.garden.dao.GardenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GardenService {

	private final GardenRepository gardenRepository;
}
