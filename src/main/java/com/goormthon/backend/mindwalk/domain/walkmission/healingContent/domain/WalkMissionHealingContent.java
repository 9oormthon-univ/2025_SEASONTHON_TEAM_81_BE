package com.goormthon.backend.mindwalk.domain.walkmission.healingContent.domain;

import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "walk_mission_healing_content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WalkMissionHealingContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "walk_mission_healing_content_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "walk_mission_id")
	private WalkMission walkMission;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "healing_content_id")
	private HealingContent healingContent;

	@Builder
	private WalkMissionHealingContent(WalkMission walkMission, HealingContent healingContent) {
		this.walkMission = walkMission;
		this.healingContent = healingContent;
	}
}
