package com.goormthon.backend.mindwalk.domain.walkmission.healingContent.domain;

import com.goormthon.backend.mindwalk.domain.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "healing_content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HealingContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "healing_content_id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Enumerated(EnumType.STRING)
	private Category category;

	@Builder
	private HealingContent(String title, String content, Category category) {
		this.title = title;
		this.content = content;
		this.category = category;
	}
}
