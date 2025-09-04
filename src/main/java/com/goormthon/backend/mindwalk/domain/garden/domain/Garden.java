package com.goormthon.backend.mindwalk.domain.garden.domain;

import java.util.ArrayList;
import java.util.List;

import com.goormthon.backend.mindwalk.domain.common.BaseTimeEntity;
import com.goormthon.backend.mindwalk.domain.user.domain.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "garden")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Garden extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "garden_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Garden(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "garden", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GardenPlant> gardenPlants = new ArrayList<>();

	public static Garden createGarden(User user) {
		return Garden.builder()
			.user(user)
			.build();
	}
}
