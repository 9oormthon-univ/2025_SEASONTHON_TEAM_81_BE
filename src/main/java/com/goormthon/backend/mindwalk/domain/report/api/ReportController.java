package com.goormthon.backend.mindwalk.domain.report.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.auth.presentation.annotation.AuthenticatedId;
import com.goormthon.backend.mindwalk.domain.report.api.docs.ReportControllerDocs;
import com.goormthon.backend.mindwalk.domain.report.application.ReportService;
import com.goormthon.backend.mindwalk.domain.report.dto.response.GetWeeklySummaryResponse;
import com.goormthon.backend.mindwalk.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController implements ReportControllerDocs {

	private final ReportService reportService;

	@GetMapping("/weekly-summary")
	public BaseResponse<GetWeeklySummaryResponse> getWeeklySummary(@AuthenticatedId Long currentUserId) {
		return BaseResponse.success(reportService.getWeeklySummary(currentUserId));
	}
}
