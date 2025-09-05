package com.goormthon.backend.mindwalk.domain.walkmission.dto.request;

import java.util.List;

import com.goormthon.backend.mindwalk.domain.walkmission.healingcontent.domain.Category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateWalkMissionRequest(
	Long targetDurationMinutes,
	@NotEmpty(message = "힐링 컨텐츠는 비어있을 수 없습니다.")
	@Size(min = 1, max = 3, message = "힐링 컨텐츠는 1개에서 3개까지 선택할 수 있습니다.")
	List<Category> healingContents
) {
}
