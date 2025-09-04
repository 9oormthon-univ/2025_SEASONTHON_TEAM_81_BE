package com.goormthon.backend.mindwalk.domain.walkmission.dto.response;

import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.domain.Category;
import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.domain.HealingContent;

public record WalkMissionInfoResponse(
	Long healingContentId,
	String title,
	Category category,
	String content
) {
	public static WalkMissionInfoResponse from(HealingContent healingContent) {
		return new WalkMissionInfoResponse(
			healingContent.getId(),
			healingContent.getTitle(),
			healingContent.getCategory(),
			healingContent.getContent()
		);
	}
}
