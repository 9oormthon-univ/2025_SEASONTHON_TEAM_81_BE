package com.goormthon.backend.mindwalk.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.goormthon.backend.mindwalk.global.exception.BaseResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {

	private final Boolean isSuccess;
	private final int code;
	private final String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final T result;

	private BaseResponse(BaseResponseStatus status, T result) {
		this.isSuccess = true;
		this.code = status.getCode();
		this.message = status.getMessage();
		this.result = result;
	}

	public static <T> BaseResponse<T> success() {
		return new BaseResponse<>(
			true,
			BaseResponseStatus.SUCCESS.getCode(),
			BaseResponseStatus.SUCCESS.getMessage(),
			null
		);
	}

	public static <T> BaseResponse<T> success(T result) {
		return new BaseResponse<>(
			true,
			BaseResponseStatus.SUCCESS.getCode(),
			BaseResponseStatus.SUCCESS.getMessage(),
			result
		);
	}

	public static <T> BaseResponse<T> fail(BaseResponseStatus status) {
		return new BaseResponse<>(
			false,
			status.getCode(),
			status.getMessage(),
			null
		);
	}
}
