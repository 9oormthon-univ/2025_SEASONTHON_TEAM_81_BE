package com.goormthon.backend.mindwalk.domain.garden.domain;

import com.goormthon.backend.mindwalk.domain.common.BaseTimeEntity;

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
@Table(name = "garden_plant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GardenPlant extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "garden_plant_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "plant_stage", nullable = false)
	private PlantStage plantStage;

	@Column(name = "growth_point", nullable = false)
	private Long growthPoint;

	@Enumerated(EnumType.STRING)
	@Column(name = "flower_type", nullable = false)
	private FlowerType flowerType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "garden_id")
	private Garden garden;

	@Builder
	public GardenPlant(PlantStage plantStage, Long growthPoint, FlowerType flowerType, Garden garden) {
		this.plantStage = plantStage;
		this.growthPoint = growthPoint;
		this.flowerType = flowerType;
		this.garden = garden;
	}

	public static GardenPlant createGardenPlant(Garden garden) {
		return GardenPlant.builder()
			.plantStage(PlantStage.SEED)
			.growthPoint(0L)
			.flowerType(FlowerType.ROSE)
			.garden(garden)
			.build();
	}

	public void addGrowthPoint(Long point) {
		this.growthPoint += point;
		if (this.growthPoint >= 150) {
			this.plantStage = PlantStage.FLOWER;
		} else if (this.growthPoint >= 50) {
			this.plantStage = PlantStage.SPROUT;
		}
	}
}
