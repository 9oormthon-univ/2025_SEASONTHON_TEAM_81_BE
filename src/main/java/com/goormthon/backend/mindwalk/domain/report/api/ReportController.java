package com.goormthon.backend.mindwalk.domain.report.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormthon.backend.mindwalk.domain.report.api.docs.ReportControllerDocs;
import com.goormthon.backend.mindwalk.domain.report.application.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController implements ReportControllerDocs {

	private final ReportService reportService;
}
