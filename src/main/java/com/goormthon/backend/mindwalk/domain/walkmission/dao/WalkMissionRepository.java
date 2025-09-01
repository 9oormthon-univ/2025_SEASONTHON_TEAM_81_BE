package com.goormthon.backend.mindwalk.domain.walkmission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMission;

@Repository
public interface WalkMissionRepository extends JpaRepository<WalkMission, Long> {
}
