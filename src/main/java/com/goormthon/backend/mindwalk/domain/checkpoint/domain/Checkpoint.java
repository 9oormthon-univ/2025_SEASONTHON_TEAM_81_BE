package com.goormthon.backend.mindwalk.domain.checkpoint.domain;

import com.goormthon.backend.mindwalk.domain.common.BaseTimeEntity;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMission;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "checkpoint")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Checkpoint extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "checkpoint_id")
	private Long id;

	@Column(nullable = false)
	private Long sequence;

	@Column(nullable = false)
	private Double latitude;

	@Column(nullable = false)
	private Double longitude;

	@Enumerated(EnumType.STRING)
	private VisitStatus status;

	@Enumerated(EnumType.STRING)
	private HealingContentType healingContentType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "walk_mission_id")
	private WalkMission walkMission;
}
