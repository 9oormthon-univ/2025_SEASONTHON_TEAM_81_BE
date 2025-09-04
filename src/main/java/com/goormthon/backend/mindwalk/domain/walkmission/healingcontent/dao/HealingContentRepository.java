package com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.domain.Category;
import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.domain.HealingContent;

@Repository
public interface HealingContentRepository extends JpaRepository<HealingContent, Long> {

	List<HealingContent> findByCategoryIn(List<Category> categories);
}
