package com.goormthon.backend.mindwalk.domain.walkmission.domain;

import java.time.LocalDateTime;

import com.goormthon.backend.mindwalk.domain.common.BaseTimeEntity;
import com.goormthon.backend.mindwalk.domain.user.domain.User;
import com.goormthon.backend.mindwalk.global.exception.BaseException;
import com.goormthon.backend.mindwalk.global.exception.BaseResponseStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "walk_mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WalkMission extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "walk_mission_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private WalkMissionStatus status;

	@Column(name = "steps")
	private Long steps;

	@Column(name = "distance_meters")
	private Long distanceMeters;

	@Column(name = "actual_duration_minutes")
	private Long actualDurationMinutes;

	@Column(name = "target_duration_minutes", nullable = false)
	private Long targetDurationMinutes;

	@Column(name = "completed_at")
	private LocalDateTime completedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	private WalkMission(WalkMissionStatus status, Long steps, Long distanceMeters, Long actualDurationMinutes,
		Long targetDurationMinutes, User user) {
		this.status = status;
		this.steps = steps;
		this.distanceMeters = distanceMeters;
		this.actualDurationMinutes = actualDurationMinutes;
		this.targetDurationMinutes = targetDurationMinutes;
		this.user = user;
	}

	public static WalkMission createWalkMission(User user, Long targetDurationMinutes) {
		return WalkMission.builder()
			.targetDurationMinutes(targetDurationMinutes)
			.status(WalkMissionStatus.IN_PROGRESS)
			.user(user)
			.build();
	}

	public void cancel() {
		validateProcessable();
		this.status = WalkMissionStatus.CANCELED;
	}

	public void complete(Long steps, Long distanceMeters, Long actualDurationMinutes) {
		validateProcessable();
		this.steps = steps;
		this.distanceMeters = distanceMeters;
		this.actualDurationMinutes = actualDurationMinutes;
		this.status = WalkMissionStatus.COMPLETED;
		this.completedAt = LocalDateTime.now();
	}

	private void validateProcessable() {
		if (this.status != WalkMissionStatus.IN_PROGRESS) {
			throw new BaseException(BaseResponseStatus.INVALID_MISSION_STATUS);
		}
	}
}
