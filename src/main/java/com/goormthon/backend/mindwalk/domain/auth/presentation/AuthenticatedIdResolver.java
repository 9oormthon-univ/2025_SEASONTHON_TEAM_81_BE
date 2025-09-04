package com.goormthon.backend.mindwalk.domain.auth.presentation;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.goormthon.backend.mindwalk.domain.auth.application.JwtProvider;
import com.goormthon.backend.mindwalk.domain.auth.presentation.annotation.AuthenticatedId;
import com.goormthon.backend.mindwalk.domain.user.dao.UserRepository;
import com.goormthon.backend.mindwalk.global.exception.BaseException;
import com.goormthon.backend.mindwalk.global.exception.BaseResponseStatus;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticatedIdResolver implements HandlerMethodArgumentResolver {
	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	private final JwtProvider jwtProvider;
	private final UserRepository userRepository;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(AuthenticatedId.class)
			&& parameter.getParameterType().equals(Long.class);
	}

	@Override
	public Object resolveArgument(@Nullable MethodParameter parameter,
		@Nullable ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		@Nullable WebDataBinderFactory binderFactory) {
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		String authHeader = request.getHeader(AUTH_HEADER);

		if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
			throw new BaseException(BaseResponseStatus.UNAUTHORIZED);

		}
		String token = authHeader.substring(BEARER_PREFIX.length());

		Long userId = jwtProvider.extractUserIdFromToken(token);

		if (userId == null) {
			throw new BaseException(BaseResponseStatus.UNAUTHORIZED);
		}
		return userRepository.findById(userId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND))
			.getId();
	}
}
