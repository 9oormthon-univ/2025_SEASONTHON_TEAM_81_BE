package com.goormthon.backend.mindwalk.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseResponseStatus {

	/**
	 * 1000 : 요청 성공
	 */
	SUCCESS(true, 1000, HttpStatus.OK, "요청이 성공하였습니다."),

	/**
	 * 400 BAD_REQUEST 잘못된 요청
	 */
	BAD_REQUEST(false, 400, HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
	MISSING_PATH_VARIABLE(false, 40001, HttpStatus.BAD_REQUEST, "경로 변수가 누락되었습니다."),
	MISSING_REQUEST_PARAM(false, 40002, HttpStatus.BAD_REQUEST, "쿼리 파라미터가 누락되었습니다."),
	MISSING_REQUEST_PART(false, 40003, HttpStatus.BAD_REQUEST, "multipart/form-data 파일이 누락되었습니다."),
	REQ_BINDING_FAIL(false, 40004, HttpStatus.BAD_REQUEST, "잘못된 request 입니다."),
	FAILED_VALIDATION(false, 40005, HttpStatus.BAD_REQUEST, "입력값이 누락되었거나, 부적절한 입력 값이 있습니다."),
	MISMATCH_PARAM_TYPE(false, 40006, HttpStatus.BAD_REQUEST, "잘못된 파라미터 타입입니다."),
	CANNOT_CANCEL_MISSION(false, 40007, HttpStatus.BAD_REQUEST, "이미 완료되었거나 취소된 미션은 취소할 수 없습니다."),

	/**
	 * 401 UNAUTHORIZED 권한없음(인증 실패)
	 */
	UNAUTHORIZED(false, 401, HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."),

	/**
	 * 403 FORBIDDEN 권한없음
	 */
	FORBIDDEN(false, 403, HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),

	/**
	 * 404 NOT_FOUND 잘못된 리소스 접근
	 */
	NOT_FOUND(false, 404, HttpStatus.NOT_FOUND, "Not Found"),
	NOT_FOUND_MISSION(false, 404, HttpStatus.NOT_FOUND, "Not Found Mission"),
	NOT_FOUND_HEALING_CONTENT(false, 404, HttpStatus.NOT_FOUND, "Not Found HealingContent"),
	NOT_FOUND_USER(false, 40401, HttpStatus.NOT_FOUND, "User를 찾을 수 없습니다."),
	NOT_FOUND_GARDEN(false, 40402, HttpStatus.NOT_FOUND, "Garden을 찾을 수 없습니다."),
	NOT_FOUND_GROWING_PLANT(false, 40403, HttpStatus.NOT_FOUND, "현재 키우고 있는 식물을 찾을 수 없습니다."),

	/**
	 * 405 METHOD_NOT_ALLOWED 지원하지 않은 method 호출
	 */
	METHOD_NOT_ALLOWED(false, 405, HttpStatus.METHOD_NOT_ALLOWED, "해당 method는 지원하지 않습니다."),

	/**
	 * 406 NOT_ACCEPTABLE 인식할 수 없는 content type
	 */
	NOT_ACCEPTABLE(false, 406, HttpStatus.NOT_ACCEPTABLE, "인식할 수 없는 미디어 타입입니다."),

	/**
	 * 409 CONFLICT 중복된 리소스
	 */
	CONFLICT(false, 409, HttpStatus.CONFLICT, "중복된 리소스입니다."),

	/**
	 * 415 UNSUPPORTED_MEDIA_TYPE 지원하지 않는 content type
	 */
	UNSUPPORTED_MEDIA_TYPE(false, 415, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "지원하지 않는 미디어 타입입니다."),

	/**
	 * 500 INTERNAL_SERVER_ERROR 서버 내부 에러
	 */
	INTERNAL_SERVER_ERROR(false, 500, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러입니다."),

	/**
	 * 503 SERVICE_UNAVAILABLE 서버 내부 에러
	 */
	SERVICE_UNAVAILABLE(false, 503, HttpStatus.SERVICE_UNAVAILABLE, "현재 서비스가 불가능한 상태입니다."),
	INTERNAL_SERVER_TIME_OUT(false, 50301, HttpStatus.SERVICE_UNAVAILABLE, "서버에서 시간초과가 발생했습니다.");

	private final boolean isSuccess;
	private final int code;
	private final HttpStatus httpStatus;
	private final String message;
}
