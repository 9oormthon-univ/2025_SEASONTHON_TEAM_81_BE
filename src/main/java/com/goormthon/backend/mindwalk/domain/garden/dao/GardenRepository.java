package com.goormthon.backend.mindwalk.domain.garden.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormthon.backend.mindwalk.domain.garden.domain.Garden;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Long> {
	Optional<Garden> findByUserId(Long userId);
}
