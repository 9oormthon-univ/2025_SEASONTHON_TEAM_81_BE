package com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.domain.WalkMissionHealingContent;

@Repository
public interface WalkMissionHealingContentRepository extends JpaRepository<WalkMissionHealingContent, Long> {
}
