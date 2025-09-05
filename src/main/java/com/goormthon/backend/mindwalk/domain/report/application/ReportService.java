package com.goormthon.backend.mindwalk.domain.report.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormthon.backend.mindwalk.domain.report.dto.response.GetWeeklySummaryResponse;
import com.goormthon.backend.mindwalk.domain.user.dao.UserRepository;
import com.goormthon.backend.mindwalk.domain.user.domain.User;
import com.goormthon.backend.mindwalk.domain.walkmission.dao.WalkMissionRepository;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMission;
import com.goormthon.backend.mindwalk.domain.walkmission.domain.WalkMissionStatus;
import com.goormthon.backend.mindwalk.global.exception.BaseException;
import com.goormthon.backend.mindwalk.global.exception.BaseResponseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final WalkMissionRepository walkMissionRepository;
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public GetWeeklySummaryResponse getWeeklySummary(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
		boolean yesterdayWalkSuccess = checkYesterdayWalkSuccess(user);
		List<WalkMission> weeklyMissions = getWeeklyCompletedMissions(user);
		long weeklyWalkCount = weeklyMissions.size();
		long weeklyDistanceMeters = weeklyMissions.stream()
			.mapToLong(WalkMission::getDistanceMeters)
			.sum();
		long weeklyDurationMinutes = weeklyMissions.stream()
			.mapToLong(WalkMission::getActualDurationMinutes)
			.sum();
		return new GetWeeklySummaryResponse(yesterdayWalkSuccess, weeklyWalkCount, weeklyDistanceMeters,
			weeklyDurationMinutes);
	}

	private boolean checkYesterdayWalkSuccess(User user) {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		LocalDateTime startOfYesterday = yesterday.atStartOfDay();
		LocalDateTime endOfYesterday = yesterday.atTime(LocalTime.MAX);
		return walkMissionRepository.existsByUserAndStatusAndCompletedAtBetween(user,
			WalkMissionStatus.COMPLETED, startOfYesterday, endOfYesterday);
	}

	private List<WalkMission> getWeeklyCompletedMissions(User user) {
		LocalDate today = LocalDate.now();
		LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
		LocalDateTime startOfWeekTime = startOfWeek.atStartOfDay();
		LocalDateTime endOfWeekTime = today.atTime(LocalTime.MAX);
		return walkMissionRepository.findAllByUserAndStatusAndCompletedAtBetween(user,
			WalkMissionStatus.COMPLETED, startOfWeekTime, endOfWeekTime);
	}
}
