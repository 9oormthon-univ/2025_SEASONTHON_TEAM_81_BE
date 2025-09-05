package com.goormthon.backend.mindwalk.domain.walkmission.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormthon.backend.mindwalk.domain.user.domain.User;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMission;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMissionStatus;

@Repository
public interface WalkMissionRepository extends JpaRepository<WalkMission, Long> {
	List<WalkMission> findAllByUserAndStatusAndCompletedAtBetween(User user, WalkMissionStatus status,
		LocalDateTime start, LocalDateTime end);

	boolean existsByUserAndStatusAndCompletedAtBetween(User user, WalkMissionStatus status, LocalDateTime start,
		LocalDateTime end);

	Long countByUserAndStatus(User user, WalkMissionStatus status);
}
